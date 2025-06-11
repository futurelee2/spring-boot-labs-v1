package com.example.ch2labs.labs04;

//### 가위바위보 게임 API
//
//- **GET** `/rps?user=scissors`
//        - 서버는 랜덤으로 `rock`, `paper`, `scissors` 중 하나 선택
//- 승/패/무 결과를 텍스트 또는 JSON으로 반환
//
//```json
//{
//    "user": "scissors",
//        "server": "rock",
//        "result": "You Lose!"
//}

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

@Controller
@ResponseBody
public class Lab4 {
    private final String[] choices = {"rock", "paper", "scissor"};
    Random random = new Random();


    @GetMapping("/rps")
    public Map<String, String> game(@RequestParam String user) {
        int r = random.nextInt(3);
        String server = choices[r];

        String result = returnPrint(user, server);

        Map<String, String> resultMap = new LinkedHashMap<>(Map.of("user", user, "server", server, "result", result));

        return resultMap;
    }


    public String returnPrint(String user, String server){
        if(user.equals(server)){
            return "Draw";
        }
        switch (user){
            case "rock":
                return (server.equals("scissor"))? "You Win" : "You Lose";

            case "paper":
                return (server.equals("rock"))? "You Win" : "You Lose";
            case "scissor":
                return (server.equals("paper"))? "You Win" : "You Lose";
            default:
                return "Invalid";
        }

    }

}
