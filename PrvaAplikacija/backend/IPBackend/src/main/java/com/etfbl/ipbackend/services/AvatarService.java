package com.etfbl.ipbackend.services;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.AttributeValue;
import com.etfbl.ipbackend.models.Avatar;
import com.etfbl.ipbackend.models.requests.AttributeValueReqeust;
import com.etfbl.ipbackend.models.requests.AvatarRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


public interface AvatarService {
    Avatar createAvatar(AvatarRequest avatar);

    String saveImage(Integer userId, MultipartFile multipartFile);

    byte[] getImage(String name);

    Optional<Avatar> getAvatarByFkUserAvatar(Integer id);

    List<Avatar> getAllAvatars();

    Avatar updateAvatar(Integer id, Avatar avatar) throws ExistingException;

    void deleteAvatar(Integer id);
}
