package hello.hellospring; // 해당 클래스가 속한 패키지 정의

import org.springframework.boot.SpringApplication; // sping boot 클래스 import
import org.springframework.boot.autoconfigure.SpringBootApplication;   // sping boot 클래스 import

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
