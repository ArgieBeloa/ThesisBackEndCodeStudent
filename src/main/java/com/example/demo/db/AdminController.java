package com.example.demo.db;

import com.example.demo.studentPackage.JWTService;
import com.example.demo.studentPackage.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private StudentRepository studentRepository;

    /**
     * ✅ Admin key validation (used internally by login or other admin tools)
     */
    @GetMapping("/check-student")
    public ResponseEntity<?> checkClient(
            @RequestHeader(value = "X-ADMIN-KEY", required = false) String adminKey,
            @RequestParam("studentNumber") String studentNumber
    ) {
        System.out.println("🟡 [Controller] Received Admin Key: " + adminKey);

        boolean valid = jwtService.isAdminKey(adminKey);
        System.out.println("🟢 [Controller] isAdminKey() result: " + valid);

        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid admin key");
        }

        boolean exists = studentRepository.existsByStudentNumber(studentNumber);
        return ResponseEntity.ok(Map.of("exists", exists));
    }

    /**
     * ✅ Admin login endpoint — returns JWT token with role = ADMIN
     */
    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(
            @RequestHeader(value = "X-ADMIN-KEY", required = false) String adminKey
    ) {
        System.out.println("🔑 [Admin Login] Received key: " + adminKey);

        // Validate admin key
        if (!jwtService.isAdminKey(adminKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid admin key"));
        }

        // Generate ADMIN token
        String token = jwtService.generateToken("admin", "ADMIN");

        return ResponseEntity.ok(Map.of(
                "message", "Admin authenticated successfully",
                "token", token
        ));
    }
}
