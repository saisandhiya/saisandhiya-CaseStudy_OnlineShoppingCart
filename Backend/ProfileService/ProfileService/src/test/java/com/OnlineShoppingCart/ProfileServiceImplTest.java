package com.OnlineShoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.OnlineShopping.Exceptions.ProfileAlreadyExistsException;
import com.OnlineShopping.Exceptions.ProfileNotFoundException;
import com.OnlineShopping.ServiceImpl.ProfileServiceImpl;
import com.OnlineShoppingCart.Entity.Address;
import com.OnlineShoppingCart.Entity.UserProfile;
import com.OnlineShoppingCart.Repository.ProfileRepository;

public class ProfileServiceImplTest {

    @InjectMocks
    private ProfileServiceImpl profileService;

    @Mock
    private ProfileRepository profileRepository;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProfilesWhenProfilesExist() throws ProfileNotFoundException {
        // Arrange
        List<UserProfile> profiles = new ArrayList<>();
        profiles.add(new UserProfile(1, "John Doe", "john@example.com", 1234567890L, LocalDate.of(1990, 1, 1), "Male", new Address()));
        profiles.add(new UserProfile(2, "Jane Smith", "jane@example.com", 9876543210L, LocalDate.of(1995, 5, 15), "Female", new Address()));
        when(profileRepository.findAll()).thenReturn(profiles);

        // Act
        List<UserProfile> resultProfiles = profileService.getAllProfiles();

        // Assert
        assertEquals(2, resultProfiles.size());
        assertEquals("John Doe", resultProfiles.get(0).getFullName());
        assertEquals("Jane Smith", resultProfiles.get(1).getFullName());
    }

    @Test
    public void testGetAllProfilesWhenNoProfilesExist() {
        // Arrange
        when(profileRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(ProfileNotFoundException.class, () -> profileService.getAllProfiles());
    }

    @Test
    public void testAddNewCustomerProfileWhenProfileDoesNotExist() throws ProfileAlreadyExistsException {
        // Arrange
        UserProfile userProfile = new UserProfile(1, "John Doe", "john@example.com", 1234567890L, LocalDate.of(1990, 1, 1), "Male", new Address());
        when(profileRepository.findById(1)).thenReturn(Optional.empty());
        when(profileRepository.insert(userProfile)).thenReturn(userProfile);

        // Act
        UserProfile addedProfile = profileService.addNewCustomerProfile(userProfile);

        // Assert
        assertEquals(1, addedProfile.getProfileId());
        assertEquals("John Doe", addedProfile.getFullName());
        assertEquals("john@example.com", addedProfile.getEmailId());
    }

    @Test
    public void testAddNewCustomerProfileWhenProfileAlreadyExists() {
        // Arrange
        UserProfile userProfile = new UserProfile(1, "John Doe", "john@example.com", 1234567890L, LocalDate.of(1990, 1, 1), "Male", new Address());
        when(profileRepository.findById(1)).thenReturn(Optional.of(userProfile));

        // Act and Assert
        assertThrows(ProfileAlreadyExistsException.class, () -> profileService.addNewCustomerProfile(userProfile));
    }

    @Test
    public void testUpdateProfileWhenProfileExists() {
        // Arrange
        UserProfile existingProfile = new UserProfile(1, "John Doe", "john@example.com", 1234567890L, LocalDate.of(1990, 1, 1), "Male", new Address());
        UserProfile updatedProfileData = new UserProfile(1, "Updated Name", "updated@example.com", 9876543210L, LocalDate.of(1995, 5, 15), "Female", new Address());
        when(profileRepository.findById(1)).thenReturn(Optional.of(existingProfile));
        when(profileRepository.save(existingProfile)).thenReturn(existingProfile);

        // Act
        UserProfile updatedProfile = profileService.updateProfile(1, updatedProfileData);

        // Assert
        assertEquals(1, updatedProfile.getProfileId());
        assertEquals("Updated Name", updatedProfile.getFullName());
        assertEquals("updated@example.com", updatedProfile.getEmailId());
    }

    @Test
    public void testUpdateProfileWhenProfileDoesNotExist() {
        // Arrange
        UserProfile updatedProfileData = new UserProfile(1, "Updated Name", "updated@example.com", 9876543210L, LocalDate.of(1995, 5, 15), "Female", new Address());
        when(profileRepository.findById(1)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ProfileNotFoundException.class, () -> profileService.updateProfile(1, updatedProfileData));
    }

    @Test
    public void testDeleteProfileWhenProfileExists() throws ProfileNotFoundException {
        // Arrange
        UserProfile userProfile = new UserProfile(1, "John Doe", "john@example.com", 1234567890L, LocalDate.of(1990, 1, 1), "Male", new Address());
        when(profileRepository.findById(1)).thenReturn(Optional.of(userProfile));

        // Act
        String result = profileService.deleteProfile(1);

        // Assert
        assertEquals("deleted successfully", result);
    }

    @Test
    public void testDeleteProfileWhenProfileDoesNotExist() {
        // Arrange
        when(profileRepository.findById(1)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ProfileNotFoundException.class, () -> profileService.deleteProfile(1));
    }

    @Test
    public void testGetByProfileIdWhenProfileExists() throws ProfileNotFoundException {
        // Arrange
        UserProfile userProfile = new UserProfile(1, "John Doe", "john@example.com", 1234567890L, LocalDate.of(1990, 1, 1), "Male", new Address());
        when(profileRepository.findById(1)).thenReturn(Optional.of(userProfile));

        // Act
        UserProfile resultProfile = profileService.getByProfileId(1);

        // Assert
        assertEquals(1, resultProfile.getProfileId());
        assertEquals("John Doe", resultProfile.getFullName());
        assertEquals("john@example.com", resultProfile.getEmailId());
    }

    @Test
    public void testGetByProfileIdWhenProfileDoesNotExist() {
        // Arrange
        when(profileRepository.findById(1)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ProfileNotFoundException.class, () -> profileService.getByProfileId(1));
    }

   
}
