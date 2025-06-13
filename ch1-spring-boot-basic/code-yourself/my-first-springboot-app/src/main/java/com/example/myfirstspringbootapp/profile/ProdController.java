package com.example.myfirstspringbootapp.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//RestController 하면 빈이됨

@RestController
@Profile("prod") // prod로 하면 조건부로 해서 생성이 안됨. 다른건 생성이 됨
public class ProdController {
    @GetMapping("/prod")
    public String prod(){
        return "운영 환경";
    }
}
