package com.etfbl.ipbackend.services.impl;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Avatar;
import com.etfbl.ipbackend.models.requests.AvatarRequest;
import com.etfbl.ipbackend.repositories.AvatarRepository;
import com.etfbl.ipbackend.services.AvatarService;
import com.etfbl.ipbackend.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class AvatarServiceImpl implements AvatarService {
    @Value("${avatarimagedir:}")
    private String dir;
    private final AvatarRepository avatarRepository;

    public AvatarServiceImpl(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    @Override
    public Avatar createAvatar(AvatarRequest avatarRequest) {
        Avatar avatar = new Avatar();
        avatar.setFkUserAvatar(avatarRequest.getFkUserAvatar());
        avatar.setName(avatarRequest.getName());
        return avatarRepository.save(avatar);
    }

    @Override
    public String saveImage(Integer userId, MultipartFile multipartFile) {
        try {
            return ImageUtils.saveFile(dir, multipartFile);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public byte[] getImage(String name) {
        try {
            return ImageUtils.getImage(dir, name);
        } catch (Exception e) {
            return new byte[0];
        }
    }

    @Override
    public Optional<Avatar> getAvatarByFkUserAvatar(Integer id) {
        return avatarRepository.getAvatarByFkUserAvatar(id);
    }

    @Override
    public List<Avatar> getAllAvatars() {
        return null;
    }

    @Override
    public Avatar updateAvatar(Integer id, Avatar avatar) throws ExistingException {
//        Optional<Avatar> avatar1 = avatarRepository.getAvatarByFkUserAvatar(id);
//        if (avatar1.isEmpty())
//            throw new ExistingException("Avatar sa ID: " + id + " ne postoji u bazi podataka");
//
//        Avatar avatar2 = avatar1.get();

        return avatarRepository.save(avatar);
    }

    @Override
    public void deleteAvatar(Integer id) {

    }
}
