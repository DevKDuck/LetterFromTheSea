package com.devkduck.letterfromthesea;

//import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.devkduck.letterfromthesea.user.mapper")
@SpringBootApplication
public class LetterfromtheseaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LetterfromtheseaApplication.class, args);
	}

}
