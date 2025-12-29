package SpringBoot.demo.Controller;

import SpringBoot.demo.Entity.Employee;
import SpringBoot.demo.Repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Employee employee) {

        employee.setRole("EMPLOYEE");
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        empRepository.save(employee);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/register-admin")
    public ResponseEntity<String> registerAdmin(@RequestBody Employee employee) {
        employee.setRole("ADMIN");
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        empRepository.save(employee);
        return ResponseEntity.ok("Admin registered successfully!");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {

        try {
            String email = loginData.get("email").trim();   // remove spaces
            String password = loginData.get("password").trim();

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            Employee emp = empRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "role", emp.getRole(),
                    "id", emp.getId()
            ));

        } catch (Exception e) {
            return ResponseEntity
                    .status(401)
                    .body("Invalid email or password");
        }
    }

}