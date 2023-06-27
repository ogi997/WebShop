package com.etfbl.ipbackend.controllers;

import com.etfbl.ipbackend.models.Users;
import com.etfbl.ipbackend.models.requests.LoginRequest;
import com.etfbl.ipbackend.models.requests.PincodeRequest;
import com.etfbl.ipbackend.models.requests.UserRequest;
import com.etfbl.ipbackend.services.EmailSenderService;
import com.etfbl.ipbackend.services.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    private final EmailSenderService emailSenderService;
    private static final Logger logger = LogManager.getLogger(UsersController.class);

    public UsersController(UsersService usersService, EmailSenderService emailSenderService) {
        this.usersService = usersService;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/loginUsers")
    public ResponseEntity<Users> loginUser(@RequestBody LoginRequest loginRequest) {
        Users user = usersService.loginUsers(loginRequest);
        logger.info("POST Login user EMAIL: " + loginRequest.getEmail());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/generate-pincode/{userId}")
    public ResponseEntity<Users> generatePincode(@PathVariable("userId") Integer id) {
        Optional<Users> optionalUsers = usersService.getUserById(id);

        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            Integer pinCodeGen = new Random().nextInt(9000) + 1000;
            users.setPinCode(pinCodeGen);
            logger.info("POST generated new pin code");
            users = usersService.updatePincode(users);

            //emailSenderService.sendEmail(users.getEmail(), "WebShop IP - ACTIVATE PINCODE", "Vas aktivacioni pin code je: "+pinCodeGen);
            logger.info("Email with pincode sent.");
            return ResponseEntity.ok(users);

        }
        logger.error("POST no content user");
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/activate-user/{userId}")
    public ResponseEntity<Users> activateUser(@PathVariable("userId") Integer id, @RequestBody PincodeRequest pincodeRequest){
        Optional<Users> optionalUsers = usersService.getUserById(id);

        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();

            if (Objects.equals(users.getPinCode(), pincodeRequest.getPinCode())) {
                logger.info("POST activate successfully account");
                users = usersService.updateActive(users, (byte) 1);
                return ResponseEntity.ok(users);
            } else {
                logger.error("POST not activated successfully account");
                return ResponseEntity.status(403).build();
            }
        }
        logger.error("POST users not found");
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UserRequest userRequest) {
        userRequest.setDeleted((byte)0);
        Users user = usersService.getUsersByUsername(userRequest.getUsername());
        if (user != null) {
            logger.error("POST create users 403 USERNAME EXIST");
            return ResponseEntity.status(403).build();
        }

        Users userEmail = usersService.getUsersByEmail(userRequest.getEmail());
        if (userEmail != null) {
            logger.error("POST create users 403 EMAIL ALREADY EXIST");
            return ResponseEntity.status(403).build();
        }

        userRequest.setActive((byte) 0);
        Integer pinCodeGen = new Random().nextInt(9000) + 1000;
        userRequest.setPinCode(pinCodeGen);
        Users users = usersService.createUser(userRequest);
        emailSenderService.sendEmail(users.getEmail(), "WebShop IP - ACTIVATE PINCODE", "Vas aktivacioni pin code: "+pinCodeGen);
        logger.info("POST successfully created user");
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") Integer id) {
        Optional<Users> usersOptional = usersService.getUserById(id);
        return usersOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = usersService.getUsers();

        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable("id") Integer id, @RequestBody UserRequest userRequest) {
        try {
            logger.info("PUT user update successfully");
            Users user = usersService.updateUser(id, userRequest);

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            logger.error("PUT users update not found");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        usersService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
