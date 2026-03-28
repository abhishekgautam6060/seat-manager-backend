package com.library.seatmanager.controller;

import com.library.seatmanager.dto.LibraryCreateRequest;
import com.library.seatmanager.entity.Admin;
import com.library.seatmanager.entity.Library;
import com.library.seatmanager.entity.Seat;
import com.library.seatmanager.repository.AdminRepository;
import com.library.seatmanager.repository.LibraryRepository;
import com.library.seatmanager.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    @Autowired
    private LibraryRepository libraryRepo;

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private SeatRepository seatRepo;

    @PostMapping
    public ResponseEntity<?> createLibrary(
            @RequestBody LibraryCreateRequest req,
            Authentication auth) {

        String phone = auth.getName();
        Admin admin = adminRepo.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        Library library = new Library();
        library.setLibraryName(req.getLibraryName());
        library.setTotalSeats(req.getTotalSeats());
        library.setLogoUrl(req.getLogoUrl());
        library.setAdmin(admin);

        // 3️⃣ SAVE LIBRARY FIRST (VERY IMPORTANT)
        Library savedLibrary = libraryRepo.save(library);

        // ===========================
        // 🔥 2.3 AUTO-CREATE SEATS
        // ===========================
        for (int i = 1; i <= savedLibrary.getTotalSeats(); i++) {
            Seat seat = new Seat();
            seat.setSeatNumber(i);
            seat.setOccupied(false);
            seat.setLibrary(savedLibrary);
            seatRepo.save(seat);
        }

        return ResponseEntity.ok(savedLibrary);
    }

    @GetMapping
    public Optional<Library> getMyLibraries(Authentication auth) {
        String phone = auth.getName();
        return libraryRepo.findFirstByAdminPhone(phone);
    }

    @GetMapping("/exists")
    public ResponseEntity<?> libraryExists(Authentication auth) {

        String phone = auth.getName();

        boolean exists = libraryRepo.existsByAdminPhone(phone);

        if (!exists) {
            return ResponseEntity.ok(
                    Map.of("exists", false)
            );
        }

        Library lib = libraryRepo.findFirstByAdminPhone(phone)
                .orElseThrow(() -> new RuntimeException("Library not found"));

        return ResponseEntity.ok(
                Map.of(
                        "exists", true,
                        "libraryId", lib.getId(),
                        "libraryName", lib.getLibraryName()
                )
        );
    }
}
