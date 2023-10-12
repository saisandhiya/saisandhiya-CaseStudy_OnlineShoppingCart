package com.OnlineShoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.OnlineShoppingCart.Entity.UserProfile;
import com.OnlineShoppingCart.Repository.ProfileRepository;

@ExtendWith(MockitoExtension.class)
public class ProfileRepositoryTest {

    @Mock
    private ProfileRepository profileRepository;

    static List<UserProfile> profiles = new ArrayList<>();

    static UserProfile userProfile;

    @BeforeAll
    public static void setUp() {
        userProfile = new UserProfile();
        userProfile.setProfileId(1);
        userProfile.setFullName("John Doe");
        userProfile.setEmailId("john@example.com");
        userProfile.setMobileNumber(1234567890L);

        profiles.add(userProfile);
    }

    @Test
    public void testGetAllProfiles() {
        when(profileRepository.findAll()).thenReturn(profiles);
        assertEquals(profiles, profileRepository.findAll());
    }

    @Test
    public void testFindByProfileId() {
        Optional<UserProfile> byProfileId = Optional.of(userProfile);
        when(profileRepository.findById(1)).thenReturn(byProfileId);
        assertEquals(byProfileId, profileRepository.findById(1));
    }
   

    @Test
    public void testSaveUserProfile() {
        when(profileRepository.save(userProfile)).thenReturn(userProfile);
        assertEquals(userProfile, profileRepository.save(userProfile));
    }

    @Test
    public void testDeleteUserProfile() {
        profileRepository.deleteById(1);
        verify(profileRepository).deleteById(1);
    }

    @Test
    public void testDeleteAllProfiles() {
        profileRepository.deleteAll();
        verify(profileRepository).deleteAll();
    }
}
