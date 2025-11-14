package com.corporated.skejac.web.controllers;

import com.corporated.skejac.domain.dto.UserXampleDto;
import com.corporated.skejac.domain.services.UserXampleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skejac/api/userXample")
public class UserXampleController {
    private final UserXampleService userXampleService;

    public UserXampleController (UserXampleService userXampleService){
        this.userXampleService=userXampleService;
    }

    //http://localhost:8080/skejac/api/userXample
    @PostMapping
    public UserXampleDto creatUserXample(@RequestBody UserXampleDto userXampleDto){
        return userXampleService.creatUserXample(userXampleDto);
    }
}
