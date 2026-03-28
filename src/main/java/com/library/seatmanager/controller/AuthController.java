package com.library.seatmanager.controller;

import com.library.seatmanager.config.JwtUtil;
import com.library.seatmanager.dto.LoginRequest;
import com.library.seatmanager.dto.OtpRequest;
import com.library.seatmanager.dto.SignupRequest;
import com.library.seatmanager.entity.Admin;
import com.library.seatmanager.repository.AdminRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

//     @Autowired
//     private AdminRepository adminRepo;

//     @Autowired
//     private PasswordEncoder encoder;


//     @PostMapping("/signup")
//     public ResponseEntity<String> signup(
//             @RequestBody SignupRequest req,
//             HttpServletRequest request) {

//         if (adminRepo.findByPhone(req.getPhone()).isPresent()) {
//             return ResponseEntity.badRequest().body("Phone already registered");
//         }

//         Admin admin = new Admin();
//         admin.setName(req.getName());
//         admin.setPhone(req.getPhone());
//         admin.setPassword(encoder.encode(req.getPassword()));
//         adminRepo.save(admin);

//         // 🔥 AUTO LOGIN AFTER SIGNUP
//         UsernamePasswordAuthenticationToken authentication =
//                 new UsernamePasswordAuthenticationToken(
//                         admin.getPhone(),
//                         null,
//                         List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
//                 );

//         SecurityContext context = SecurityContextHolder.createEmptyContext();
//         context.setAuthentication(authentication);

//         HttpSession session = request.getSession(true);
//         session.setAttribute(
//                 HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
//                 context
//         );

//         return ResponseEntity.ok("Signup successful");
//     }


//     @PostMapping("/login")
//     public ResponseEntity<String> login(@RequestBody LoginRequest req,
//                                         HttpServletRequest request) {

//         Admin admin = adminRepo.findByPhone(req.getPhone())
//                 .orElseThrow(() -> new RuntimeException("Admin not found"));

//         if (!encoder.matches(req.getPassword(), admin.getPassword())) {
//             return ResponseEntity.status(401).body("Invalid credentials");
//         }

//         UsernamePasswordAuthenticationToken authentication =
//                 new UsernamePasswordAuthenticationToken(
//                         admin.getPhone(),
//                         null,
//                         List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
//                 );

//         SecurityContext context = SecurityContextHolder.createEmptyContext();
//         context.setAuthentication(authentication);

//         HttpSession session = request.getSession(true);
//         session.setAttribute(
//                 HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
//                 context
//         );

//         return ResponseEntity.ok("Login success");
//     }

//     @PostMapping("/verify-otp")
//     public ResponseEntity<String> verifyOtp(@RequestBody OtpRequest req,
//                                             HttpServletRequest request) {


//         String phone = (String) request.getSession().getAttribute("PHONE");

//         Admin admin = adminRepo.findByPhone(phone)
//                 .orElseThrow(() -> new RuntimeException("Admin not found"));

//         if (!admin.getOtp().equals(req.getOtp())
//                 || admin.getOtpExpiry().isBefore(LocalDateTime.now())) {
//             return ResponseEntity.status(401).body("Invalid OTP");
//         }

//         UsernamePasswordAuthenticationToken authentication =
//                 new UsernamePasswordAuthenticationToken(
//                         admin.getPhone(),
//                         null,
//                         List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
//                 );

//         SecurityContext context = SecurityContextHolder.createEmptyContext();
//         context.setAuthentication(authentication);

//         HttpSession session = request.getSession(true);
//         session.setAttribute(
//                 HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
//                 context
//         );
//         // Optional cleanup
//         admin.setOtp(null);
//         admin.setOtpExpiry(null);
//         adminRepo.save(admin);

//         return ResponseEntity.ok("Login success");
//     }

//     @GetMapping("/api/debug/auth")
//     public String debugAuth(Authentication auth) {
//         return auth == null ? "NOT_AUTHENTICATED" : auth.getName();
//     }




    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {

        if (adminRepo.findByPhone(req.getPhone()).isPresent()) {
            return ResponseEntity.badRequest().body("Phone already registered");
        }

        Admin admin = new Admin();
        admin.setName(req.getName());
        admin.setPhone(req.getPhone());
        admin.setPassword(encoder.encode(req.getPassword()));

        adminRepo.save(admin);

        String token = jwtUtil.generateToken(admin.getPhone());

        return ResponseEntity.ok(
                Collections.singletonMap("token", token)
        );
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {

        Admin admin = adminRepo.findByPhone(req.getPhone())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (!encoder.matches(req.getPassword(), admin.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtUtil.generateToken(admin.getPhone());

        return ResponseEntity.ok(
                Collections.singletonMap("token", token)
        );
    }



}
