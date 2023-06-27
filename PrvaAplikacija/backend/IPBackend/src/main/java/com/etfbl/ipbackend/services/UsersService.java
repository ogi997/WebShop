package com.etfbl.ipbackend.services;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Users;
import com.etfbl.ipbackend.models.requests.LoginRequest;
import com.etfbl.ipbackend.models.requests.PincodeRequest;
import com.etfbl.ipbackend.models.requests.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    Users createUser(UserRequest userRequest);

    Users updateActive(Users users, Byte active);

    Users updatePincode(Users users);

    Users loginUsers(LoginRequest loginRequest);

//    Users activateAccount(Integer id, PincodeRequest pincodeRequest);

    Optional<Users> getUserById(Integer id);

    List<Users> getUsers();

    Users getUsersByUsername(String username);

    Users getUsersByEmail(String email);

    Users updateUser(Integer id, UserRequest usersRequest) throws ExistingException;

    void deleteUser(Integer id);
}
