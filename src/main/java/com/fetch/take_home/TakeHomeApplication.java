package com.fetch.take_home;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TakeHomeApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TakeHomeApplication.class, args);

		File file = new File("recieptsDataBase.json");

		if (!file.createNewFile()) {
			file.delete();
			file.createNewFile();
		}

	}

}
