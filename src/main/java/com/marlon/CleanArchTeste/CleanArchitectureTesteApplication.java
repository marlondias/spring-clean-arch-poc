package com.marlon.CleanArchTeste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CleanArchitectureTesteApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(CleanArchitectureTesteApplication.class, args);
	}

}
