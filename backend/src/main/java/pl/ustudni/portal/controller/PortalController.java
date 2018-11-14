package pl.ustudni.portal.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ustudni.portal.Message;

@RestController
public class PortalController {

    @RequestMapping("/secured")
    public Message secured() {
        return new Message("secured");
    }

    @RequestMapping("/non-secured")
    public Message nonSecured() {
        return new Message("non secured");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Message admin() {
        return new Message("for admin only");
    }

}
