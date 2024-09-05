package lacopet.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lacopet.demo.model.RegisterRequest;
import lacopet.demo.repository.LoginRepository;
import lacopet.demo.model.User;


@RestController
public class LoginController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private LoginRepository userRepository;

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String name = request.getName();
        String password = request.getPassword();

        User user = userRepository.findByName(name);
        
        if (user != null) {
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return ResponseEntity.ok(name);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha incorreta");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha incorreta");
        }
    
    }
}
