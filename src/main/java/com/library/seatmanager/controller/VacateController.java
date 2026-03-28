package com.library.seatmanager.controller;

import com.library.seatmanager.entity.Admin;
import com.library.seatmanager.entity.Seat;
import com.library.seatmanager.entity.Student;
import com.library.seatmanager.repository.AdminRepository;
import com.library.seatmanager.repository.SeatRepository;
import com.library.seatmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/vacate")
@CrossOrigin
public class VacateController {

    @Autowired
    private SeatRepository seatRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private AdminRepository adminRepo;

    @PostMapping("/libraryId/{libraryId}/seatId/{seatNumber}")
    public ResponseEntity<String> vacateSeat(Authentication auth,   @PathVariable long libraryId, @PathVariable int seatNumber) {


        String phone = auth.getName();
        Admin admin = adminRepo.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        Seat seat = seatRepo
                .findByLibraryIdAndSeatNumber(libraryId, seatNumber)
                .orElseThrow(() -> new RuntimeException("Seat not found"));


        Optional<Student> studentOpt =
                studentRepo.findBySeat_Library_IdAndSeat_SeatNumberAndActiveTrue(libraryId, seat.getSeatNumber());

        if (studentOpt.isEmpty()) {
            seat.setOccupied(false);
            seatRepo.save(seat);
            return ResponseEntity.ok("Seat already vacant");
        }

        // Student student = studentRepo
        //         .findBySeat_SeatNumberAndActiveTrue(seatNumber)
        //         .orElseThrow(() -> new RuntimeException("Active student not found"));

        // deactivate student
        studentOpt.get().setActive(false);
        studentRepo.save(studentOpt.get());

        // free seat
        seat.setOccupied(false);
        seatRepo.save(seat);

        return ResponseEntity.ok("Seat vacated successfully");
    }
}
