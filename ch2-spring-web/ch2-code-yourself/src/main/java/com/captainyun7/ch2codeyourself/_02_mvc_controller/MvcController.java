package com.captainyun7.ch2codeyourself._02_mvc_controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// html 방식
// viewResolver 로 html을 렌더링해서 보내줌
// return은 파일의 이름이어야함.
// 템플릿 폴더에 넣어주면 됨 (jsp, 타임리프 - 잘 사용X)
// jsp, 타임리프 - 템플릿엔진
// mvc 는 잘 안사용함. 마지막 강의..

@Controller
@RequestMapping("/mvc") // 한번에 받을때는 class에 붙여줌
public class MvcController {
    //mvc/basic/view
    @GetMapping("/basic/view")
    public String basicView(){
        return "basic-view"; // Controller -> view resolver 호출함
    }

    // mvc 패턴(자주쓰는 유형) :  Model(두개를 연동시킬 동적인 데이터를 모델에 담아서 전달) View(화면 보여지는 것) Controller(요청 응답-처리)
    // 유지보수, 역할 분리
    @GetMapping("/model")
    public String model(Model model){
        model.addAttribute("msg", "Hello World"); //  데이터를 모델 어트리뷰트를 통해서 뷰에 넘겨줌 (key:value 형태)
        model.addAttribute("currentTime", java.time.LocalDate.now());
        return "model-view"; // 템플릿 엔진 사용
    }
}
