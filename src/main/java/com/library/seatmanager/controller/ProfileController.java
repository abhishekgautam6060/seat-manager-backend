package com.library.seatmanager.controller;

import com.library.seatmanager.dto.ProfileDetailsResponse;
import com.library.seatmanager.entity.Admin;
import com.library.seatmanager.entity.Library;
import com.library.seatmanager.repository.AdminRepository;
import com.library.seatmanager.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private LibraryRepository libraryRepo;

    @GetMapping("/library/{libraryId}")
    public ProfileDetailsResponse getProfile(@PathVariable Long libraryId, Authentication auth) {

        String phone = auth.getName();

        Admin admin = adminRepo.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        Library lib = libraryRepo.findById(libraryId).get();

        return new ProfileDetailsResponse(
                admin.getName(),
                lib.getTotalSeats(),
                lib.getLibraryName(),
                admin.getPhone()
        );
    }
}
