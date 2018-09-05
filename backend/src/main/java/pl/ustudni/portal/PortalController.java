package pl.ustudni.portal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
