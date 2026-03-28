package com.library.seatmanager.controller;

import com.library.seatmanager.dto.HeaderInfoResponse;
import com.library.seatmanager.entity.Admin;
import com.library.seatmanager.entity.Library;
import com.library.seatmanager.repository.AdminRepository;
import com.library.seatmanager.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/header")
public class HeaderController {

    @Autowired
    private LibraryRepository libraryRepo;

    @Autowired
    private AdminRepository adminRepo;

    @GetMapping
    public HeaderInfoResponse getHeaderInfo(Authentication auth) {

        // logged-in admin phone
        String phone = auth.getName();

        Admin admin = adminRepo.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        Library lib = libraryRepo.findAll().get(0); // single library

        return new HeaderInfoResponse(
                lib.getLibraryName(),
                lib.getLogoUrl(),
                admin.getName()
        );
    }
}
