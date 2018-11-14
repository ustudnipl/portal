package pl.ustudni.portal.service;

import pl.ustudni.portal.dto.UserDto;
import pl.ustudni.portal.model.User;

public interface UserService {
    User findUserByEmail(String email);

    void createUserAccount(UserDto user);
}