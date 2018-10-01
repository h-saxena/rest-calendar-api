package com.hemant.cal.security;

import com.hemant.cal.model.AppCal;
import com.hemant.cal.model.AuthClient;
import org.springframework.web.bind.annotation.*;

@RestController
public class SecurityResource {

    @PostMapping("/token")
    public AppCal generateToken(@RequestBody AuthClient authClient) {
        return null; //AuthenticationUtils.generateToken(authClient);
    }

}
