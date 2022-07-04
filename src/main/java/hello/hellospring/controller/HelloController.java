package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //컨트롤러 어노테이션(얘는 컨트롤러야~)을 보고 스프링이 컨테이너에서 빈을 관리
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello");
        return "hello";
    }

    //
// MVC방식
    @GetMapping("hello-MVC") //localhost:8080/hello-MVC 요청이 들어오면 아래의 함수를 실행시켜줘~ 라는뜻

    public String helloMVC(@RequestParam(name = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template"; //resouces의 templates에서 hello-template라는 이름의 View를 찾아서 반환해~
    }

    //    API방식
    @GetMapping("hello-string")
    @ResponseBody //html의 <body>가 아니고 http통신 프로토콜에서 body 부분에 데이터를 직접 넣겠다 .
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    //    데이터를 내놔 API예제
    @GetMapping("hello-API")
    @ResponseBody //Json으로 반환하는게 default값 필요시에 XML로도 세팅가능하다(레거시프로젝트경우)
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name); //파라미터로 넘어온 name 전달
        return hello; //hello라는 객체를 넘김=> Spring은 HttpMessageConverter가 객체를 웹브라우저든 서버든 요청한곳에 Json 방식 데이터를 만들어서 전달, MappingJackson2HttpMessageConverter가 객체를 처리
    }
    static class Hello{
        private String name; // 1. private이기때문에 외부에선 사용 못하니까 자바bean규약

        public String getName() {  // 2.외부에서 사용할때 메서드를 통해 프로퍼티방식으로 name을 전달
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
