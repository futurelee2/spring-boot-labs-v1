package com.example.di_with_assembler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 내부적으로 component 포함.
// 빈으로 등록되어야 의존성 주입도 가능
@RestController
public class CoffeeController {
    @GetMapping("/coffee")
    public String coffee() {
        return Assembler.assemble();
    }
}
