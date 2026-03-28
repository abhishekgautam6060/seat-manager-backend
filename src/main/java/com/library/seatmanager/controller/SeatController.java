package com.library.seatmanager.controller;

import com.library.seatmanager.dto.SeatStatusDTO;
import com.library.seatmanager.entity.Admin;
import com.library.seatmanager.entity.Library;
import com.library.seatmanager.entity.Seat;
import com.library.seatmanager.repository.AdminRepository;
import com.library.seatmanager.repository.LibraryRepository;
import com.library.seatmanager.repository.SeatRepository;
import com.library.seatmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/seats")
@CrossOrigin
public class SeatController {

    @Autowired
    private SeatRepository seatRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private LibraryRepository libraryRepo;

    @Autowired
    private AdminRepository adminRepo;

    @GetMapping
    public List<SeatStatusDTO> getAllSeats(Authentication auth) {

        // 🔐 Get logged-in phone from JWT
        String phone = auth.getName();

        // 🔍 Find admin
        Admin admin = adminRepo.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Admin not found"));  

        List<Seat> seats = seatRepo.findAll(Sort.by("seatNumber"));
        List<SeatStatusDTO> result = new ArrayList<>();

        for (Seat seat : seats) {
            boolean occupied = studentRepo
                    .findBySeat_SeatNumberAndActiveTrue(seat.getSeatNumber())
                    .isPresent();

            result.add(new SeatStatusDTO(seat.getSeatNumber(), occupied));
        }
        return result;
    }
//
//    @GetMapping("/library/{libraryId}")
//    public List<SeatStatusDTO> getSeatsByLibrary(
//            @PathVariable Long libraryId,
//            Authentication auth) {
//
//        Library lib = libraryRepo.findById(libraryId)
//                .orElseThrow();
//
//        if (!lib.getAdmin().getPhone().equals(auth.getName())) {
//            throw new RuntimeException("Unauthorized");
//        }
//
//        List<Seat> seats = seatRepo.findByLibraryIdOrderBySeatNumberAsc(libraryId);
//
//        List<SeatStatusDTO> result = new ArrayList<>();
//
//        for (Seat seat : seats) {
//            boolean occupied = studentRepo
//                    .findBySeat_Library_IdAndSeat_SeatNumberAndActiveTrue(
//                            libraryId,
//                            seat.getSeatNumber()
//                    )
//                    .isPresent();
//
//            result.add(new SeatStatusDTO(seat.getSeatNumber(), occupied));
//        }
//
//        return result;
//    }

    @GetMapping("/library/{libraryId}")
    public List<SeatStatusDTO> getSeatsByLibrary(
            @PathVariable Long libraryId,
            Authentication auth) {

        if (auth == null || auth.getName() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No auth");
        }

        Library lib = libraryRepo.findById(libraryId)
                .orElseThrow();

        if (!lib.getAdmin().getPhone().equals(auth.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized");
        }

        List<Seat> seats = seatRepo.findByLibraryIdOrderBySeatNumberAsc(libraryId);

        List<SeatStatusDTO> result = new ArrayList<>();

        for (Seat seat : seats) {

            boolean occupied = false;

            try {
                occupied = studentRepo
                        .findBySeat_Library_IdAndSeat_SeatNumberAndActiveTrue(
                                libraryId,
                                seat.getSeatNumber()
                        )
                        .isPresent();
            } catch (Exception e) {
                System.out.println("Error checking seat: " + seat.getSeatNumber());
                e.printStackTrace();
            }

            result.add(new SeatStatusDTO(seat.getSeatNumber(), occupied));
        }

        return result;
    }
}

