package hello.hellospring.controller; // 해당 클래스가 속한 패키지 정의

// 필요한 스프링 클래스 임포트
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 아래 클래스가 스프링 MVC가 사용하는 컨트롤러임을 명시하는 어노테이션
public class HelloController {
    @GetMapping("hello") // /hello 요청이 들어왔을 때, 아래 메서드 실행
    public String hello(Model model) {  //Model: 자바와 클라이언트(html)틑 사이에 교환하는 객체
        model.addAttribute("data", "hello world");
        model.addAttribute("name", "권동욱");
        model.addAttribute("dept", "computer science");
        return "hello"; // 뷰(resource 아래 hello.html 파일을 브라우져에 보내줌)
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name,
                           @RequestParam("dept") String dept,
                           @RequestParam("year") String year,
                           Model model) {
        model.addAttribute("name", name);
        model.addAttribute("dept", dept);
        model.addAttribute("year", year);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name,
                              @RequestParam("dept") String dept,
                              @RequestParam("year") String year) {
        return "안녕하세요" + name + "입니다 소속은" + dept + "이고 학년은" + year + "입니다.";
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name,
                          @RequestParam("dept") String dept,
                          @RequestParam("year") String year) {
        Hello hello = new Hello();
        hello.setName(name, dept, year);
        return hello;
    }
    static class Hello {
        private String name;
        private String dept;
        private String year;
        public String getName() {
            return name;
        }
        public String getDept() {
            return dept;
        }
        public String getYear() {
            return year;
        }
        public void setName(String name, String dept, String year) {
            this.name = name;
            this.dept = dept;
            this.year = year;
        }
    }


}
// Thymeleaf : 동적 html파일 관리하는
// templates: 동적 뷰