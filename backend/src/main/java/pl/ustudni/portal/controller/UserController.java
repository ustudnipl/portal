package pl.ustudni.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.ustudni.portal.dto.UserDto;
import pl.ustudni.portal.exception.UserAlreadyExistsException;
import pl.ustudni.portal.model.User;
import pl.ustudni.portal.service.UserService;
import pl.ustudni.portal.service.VerificationTokenService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @GetMapping("/me")
    public ResponseEntity getMyself() {
        //Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        //return new ResponseEntity(authorities.toString(), HttpStatus.OK);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity(userService.findUserByEmail(auth.getName()), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@Valid @RequestBody final UserDto userDto) {
        User userExists = userService.findUserByEmail(userDto.getEmail());

        if (userExists != null)
            throw new UserAlreadyExistsException();

        userService.createUserAccount(userDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity confirmRegistration(@PathVariable final String token) {
        verificationTokenService.verifyEmail(token);

        return new ResponseEntity(HttpStatus.OK);
    }






}
