package com.OnlineShoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.OnlineShopping.ServiceImpl.ProfileServiceImpl;
import com.OnlineShoppingCart.Controller.ProfileController;
import com.OnlineShoppingCart.Entity.Address;
import com.OnlineShoppingCart.Entity.UserProfile;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private ProfileServiceImpl profileService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewCustomerProfile() {
        // Arrange
        UserProfile userProfile = new UserProfile();
        userProfile.setProfileId(1);
        userProfile.setFullName("John Doe");
        userProfile.setEmailId("john@example.com");
        userProfile.setMobileNumber(1234567890L);
        userProfile.setDateOfBirth(LocalDate.of(1990, 1, 15));
        userProfile.setGender("Male");
        Address address = new Address();
        address.setHouseNumber(123);
        address.setStreetName("Main Street");
        address.setColonyName("Downtown");
        address.setCity("City");
        address.setState("State");
        address.setPincode(12345);
        userProfile.setAddress(address);

        when(profileService.addNewCustomerProfile(any(UserProfile.class))).thenReturn(userProfile);

        // Act
        ResponseEntity<UserProfile> response = profileController.addNewCustomerProfile(userProfile);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userProfile, response.getBody());
    }

    @Test
    public void testGetAllProfiles() {
        // Arrange
        List<UserProfile> profiles = new ArrayList<>();
        UserProfile userProfile1 = new UserProfile();
        userProfile1.setProfileId(1);
        userProfile1.setFullName("John Doe");
        userProfile1.setEmailId("john@example.com");
        userProfile1.setMobileNumber(1234567890L);
        userProfile1.setDateOfBirth(LocalDate.of(1990, 1, 15));
        userProfile1.setGender("Male");
        Address address1 = new Address();
        address1.setHouseNumber(123);
        address1.setStreetName("Main Street");
        address1.setColonyName("Downtown");
        address1.setCity("City");
        address1.setState("State");
        address1.setPincode(12345);
        userProfile1.setAddress(address1);

        UserProfile userProfile2 = new UserProfile();
        userProfile2.setProfileId(2);
        userProfile2.setFullName("Jane Doe");
        userProfile2.setEmailId("jane@example.com");
        userProfile2.setMobileNumber(9876543210L);
        userProfile2.setDateOfBirth(LocalDate.of(1985, 5, 20));
        userProfile2.setGender("Female");
        Address address2 = new Address();
        address2.setHouseNumber(456);
        address2.setStreetName("Park Avenue");
        address2.setColonyName("Uptown");
        address2.setCity("City");
        address2.setState("State");
        address2.setPincode(54321);
        userProfile2.setAddress(address2);

        profiles.add(userProfile1);
        profiles.add(userProfile2);

        when(profileService.getAllProfiles()).thenReturn(profiles);

        // Act
        ResponseEntity<List<UserProfile>> response = profileController.getAllProfiles();

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(profiles, response.getBody());
    }

    @Test
    public void testGetByProfileId() {
        // Arrange
        UserProfile userProfile = new UserProfile();
        userProfile.setProfileId(1);
        userProfile.setFullName("John Doe");
        userProfile.setEmailId("john@example.com");
        userProfile.setMobileNumber(1234567890L);
        userProfile.setDateOfBirth(LocalDate.of(1990, 1, 15));
        userProfile.setGender("Male");
        Address address = new Address();
        address.setHouseNumber(123);
        address.setStreetName("Main Street");
        address.setColonyName("Downtown");
        address.setCity("City");
        address.setState("State");
        address.setPincode(12345);
        userProfile.setAddress(address);

        when(profileService.getByProfileId(1)).thenReturn(userProfile);

        // Act
        ResponseEntity<UserProfile> response = profileController.getByProfileId(1);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userProfile, response.getBody());
    }

    @Test
    public void testDeleteProfile() {
        // Arrange
        int profileId = 1;
        when(profileService.deleteProfile(profileId)).thenReturn("Profile deleted successfully");

        // Act
        ResponseEntity<String> response = profileController.deleteProfile(profileId);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Profile deleted successfully", response.getBody());
    }

    @Test
    public void testUpdateProfile() {
        // Arrange
        int profileId = 1;
        UserProfile userProfile = new UserProfile();
        userProfile.setProfileId(1);
        userProfile.setFullName("Updated Name");
        userProfile.setEmailId("updated@example.com");
        userProfile.setMobileNumber(9876543210L);
        userProfile.setDateOfBirth(LocalDate.of(1995, 8, 10));
        userProfile.setGender("Female");
        Address address = new Address();
        address.setHouseNumber(456);
        address.setStreetName("Updated Street");
        address.setColonyName("Updated Colony");
        address.setCity("Updated City");
        address.setState("Updated State");
        address.setPincode(54321);
        userProfile.setAddress(address);

        when(profileService.updateProfile(profileId, userProfile)).thenReturn(userProfile);

        // Act
        ResponseEntity<UserProfile> response = profileController.updateProfile(profileId, userProfile);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userProfile, response.getBody());
    }
}
