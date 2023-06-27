package com.etfbl.ipbackend.services.impl;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Users;
import com.etfbl.ipbackend.models.requests.LoginRequest;
import com.etfbl.ipbackend.models.requests.PincodeRequest;
import com.etfbl.ipbackend.models.requests.UserRequest;
import com.etfbl.ipbackend.repositories.UsersRepository;
import com.etfbl.ipbackend.services.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users createUser(UserRequest userRequest) {
        Users user = new Users();
        user.setActive(userRequest.getActive());
        user.setDeleted(userRequest.getDeleted());
//        user.setAvatar(userRequest.getAvatar());
        user.setCity(userRequest.getCity());
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPassword(userRequest.getPassword());
        user.setPinCode(userRequest.getPinCode());
        user.setUsername(userRequest.getUsername());
        return usersRepository.save(user);
    }

    @Override
    public Users updateActive(Users users, Byte active) {
        users.setActive(active);
        return usersRepository.save(users);
    }

    @Override
    public Users updatePincode(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public Users loginUsers(LoginRequest loginRequest) {
        return usersRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
    }

//    @Override
//    public Users activateAccount(Integer id, PincodeRequest pincodeRequest) {
//        Optional<Users>
//    }

    @Override
    public Optional<Users> getUserById(Integer id) {
        return usersRepository.findById(id);
    }

    @Override
    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users getUsersByUsername(String username) {
        return usersRepository.getUsersByUsername(username);
    }

    @Override
    public Users getUsersByEmail(String email) {
        return usersRepository.getUsersByEmail(email);
    }

    @Override
    public Users updateUser(Integer id, UserRequest usersRequest) throws ExistingException {
        Optional<Users> usersOptional = usersRepository.findById(id);

        if (usersOptional.isEmpty())
            throw new ExistingException("Users sa ID: " + id + " ne postoji u bazi podataka");

        Users user = usersOptional.get();
        //user.setActive(usersRequest.getActive());
//        user.setAvatar(usersRequest.getAvatar());
        user.setDeleted(usersRequest.getDeleted());
        user.setCity(usersRequest.getCity());
        user.setEmail(usersRequest.getEmail());
        user.setFirstName(usersRequest.getFirstName());
        user.setLastName(usersRequest.getLastName());
        user.setPassword(usersRequest.getPassword());
      //  user.setPinCode(usersRequest.getPinCode());
      //  user.setUsername(usersRequest.getUsername());
        return usersRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        usersRepository.deleteById(id);
    }
}
