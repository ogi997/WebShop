package com.etfbl.ipbackend.controllers;

import com.etfbl.ipbackend.models.Avatar;
import com.etfbl.ipbackend.models.requests.AvatarRequest;
import com.etfbl.ipbackend.services.AvatarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/avatars")
public class AvatarController {
    private final AvatarService avatarService;
    private static final Logger logger = LogManager.getLogger(AvatarController.class);

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Avatar> createDefaultAvatar(@PathVariable Integer userId) {
        AvatarRequest avatarRequest = new AvatarRequest();
        avatarRequest.setFkUserAvatar(userId);
        avatarRequest.setName("defaultAvatar.jpeg");
        Avatar avatar = avatarService.createAvatar(avatarRequest);
        logger.info("POST dodan je default avatar za korisnika");
        return ResponseEntity.ok(avatar);

    }

    @PostMapping
    public ResponseEntity<Avatar> createAvatar(@RequestParam("avatar")MultipartFile file, @RequestParam("userId") Integer id) {
        AvatarRequest avatarRequest = new AvatarRequest();
        avatarRequest.setFkUserAvatar(id);
        String name = avatarService.saveImage(id, file);
        if (name == null) {
            logger.error("POST kreiranje avatara BAD REQUEST");
            return ResponseEntity.badRequest().build();
        }

        avatarRequest.setName(name);

        Avatar avatar = avatarService.createAvatar(avatarRequest);
        logger.info("POST uspjesno kreiran avatar");
        return ResponseEntity.ok(avatar);
    }

    @PutMapping()
    public ResponseEntity<Avatar> updateAvatarByUserId(@RequestParam("userId") Integer userId, @RequestParam("avatar") MultipartFile file) {
        Optional<Avatar> avatarOptional = avatarService.getAvatarByFkUserAvatar(userId);

        if (avatarOptional.isEmpty()) {
            logger.error("PUT AVATAR NOT FOUND");
            return ResponseEntity.notFound().build();
        }

        Avatar avatar = avatarOptional.get();

        String name = avatarService.saveImage(userId, file);
        if (name == null) {
            logger.error("PUT AVATAR bad request");
            return ResponseEntity.badRequest().build();
        }

        avatar.setName(name);

        try {
            logger.info("PUT uspjesno dodan novi avatar");
            avatarService.updateAvatar(avatar.getId(), avatar);
        } catch (Exception e) {
            logger.error("PUT no content");
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(avatar);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getAvatarByUserId(@PathVariable("userId") Integer userId) {
        Optional<Avatar> avatar = avatarService.getAvatarByFkUserAvatar(userId);
        if (avatar.isEmpty()){
            logger.error("GET avatar not found.");
            return new byte[0];
        }
        logger.info("GET avatar is found");
        return avatarService.getImage(avatar.get().getName());
    }
}
