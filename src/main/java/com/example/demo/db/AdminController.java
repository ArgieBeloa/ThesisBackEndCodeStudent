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


/*
 const res = await axios.get(`${BASE_URL}/api/admin/check-client`, {
  headers: { "X-ADMIN-KEY": "Capstone_Admin_2025" },
  params: { studentNumber: "2025-0001" },
});

console.log(res.data); // { exists: true }


 */
}
