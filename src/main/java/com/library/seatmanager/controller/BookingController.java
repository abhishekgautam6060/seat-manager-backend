package com.library.seatmanager.controller;

import com.library.seatmanager.dto.BookingRequest;
import com.library.seatmanager.dto.BulkBookingRequest;
import com.library.seatmanager.dto.StudentCreateRequest;
import com.library.seatmanager.entity.Admin;
import com.library.seatmanager.entity.Library;
import com.library.seatmanager.entity.Seat;
import com.library.seatmanager.entity.Student;
import com.library.seatmanager.repository.AdminRepository;
import com.library.seatmanager.repository.LibraryRepository;
import com.library.seatmanager.repository.SeatRepository;
import com.library.seatmanager.repository.StudentRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
@CrossOrigin
public class BookingController {

    @Autowired
    private SeatRepository seatRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private LibraryRepository libraryRepo;

    @Autowired
    private AdminRepository adminRepo;

    @PostMapping()
    public ResponseEntity<String> bookSeat( Authentication authentication,  @RequestBody BookingRequest req) {


         // 🔐 Get logged-in phone from JWT
        String phone = authentication.getName();

        // 🔍 Find admin
        Admin admin = adminRepo.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Admin not found"));  

        // finding library
        Library library = libraryRepo.findById(req.getLibraryId())
                .orElseThrow(() -> new RuntimeException("Library not found"));

        // 1️⃣ Find seat by library + seat number
        Seat seat = seatRepo
                .findByLibraryIdAndSeatNumber(req.getLibraryId(), req.getSeatNumber())
                .orElseThrow(() -> new RuntimeException("Seat not found for this library"));

        // 2️⃣ Check if seat already occupied (ACTIVE student)
        Optional<Student> activeStudent =
                studentRepo.findBySeat_Library_IdAndSeat_SeatNumberAndActiveTrue(
                        req.getLibraryId(),
                        req.getSeatNumber()
                );

        if (activeStudent.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Seat already occupied");
        }

        // 3️⃣ Create student
        Student student = new Student();
        student.setName(req.getName());
        student.setPhone(req.getPhone());
        student.setSeat(seat);
        student.setSeatNumber(seat.getSeatNumber());
        student.setAmountPaid(req.getAmountPaid());
        student.setBookingDate(LocalDate.now());
        student.setExpiryDate(LocalDate.now().plusDays(30));
        student.setEndDate(LocalDateTime.now().plusDays(30));
        student.setStudentType(req.getStudentType());
        student.setLibrary(library);
        student.setActive(true);

        studentRepo.save(student);

        return ResponseEntity.ok("Seat booked successfully");
    }


    @Transactional
    @PostMapping("/bulk/library/{libraryId}")
    public ResponseEntity<?> bookMultipleSeats( Authentication authentication, @PathVariable Long libraryId,
            @RequestBody BulkBookingRequest request) {

        // 🔍 Find Library
        Library library = libraryRepo.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("Library not found"));

        List<String> bookedSeats = new ArrayList<>();
        List<String> failedSeats = new ArrayList<>();

        for (StudentCreateRequest dto : request.getStudents()) {

            try {

                // 1️⃣ Check Seat exists in that library
                Seat seat = seatRepo
                        .findByLibraryIdAndSeatNumber(
                                request.getLibraryId(),
                                dto.getSeatNumber())
                        .orElseThrow(() ->
                                new RuntimeException("Seat not found"));

                // 2️⃣ Check seat already occupied
                Optional<Student> activeStudent =
                        studentRepo.findBySeat_Library_IdAndSeat_SeatNumberAndActiveTrue(
                                request.getLibraryId(),
                                dto.getSeatNumber()
                        );

                if (activeStudent.isPresent()) {
                    failedSeats.add("Seat " + dto.getSeatNumber() + " already occupied");
                    continue;
                }

                // 3️⃣ Create Student
                Student student = new Student();
                student.setName(dto.getName());
                student.setPhone(dto.getPhone());
                student.setSeat(seat);
                student.setSeatNumber(seat.getSeatNumber());
                student.setAmountPaid(dto.getAmount());
                student.setBookingDate(LocalDate.now());
                student.setExpiryDate(dto.getExpiryDate());
                student.setEndDate(dto.getEndDate());
                student.setStudentType(dto.getStudentType());
                student.setLibrary(library);
                student.setActive(true);

                studentRepo.save(student);

                bookedSeats.add("Seat " + dto.getSeatNumber() + " booked");

            } catch (Exception e) {
                failedSeats.add("Seat " + dto.getSeatNumber() + " failed");
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("success", bookedSeats);
        response.put("failed", failedSeats);

        return ResponseEntity.ok(response);
    }


}

