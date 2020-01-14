package com.gxpinheiro.filecreator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class FileCreatorApp {

	public static void main(String[] args) {
		SpringApplication.run(FileCreatorApp.class, args);
		
		final ExecutorService executor = Executors.newFixedThreadPool(2);

		for (int i = 1; i <= 10; i++) {
			final Integer current = i;
            executor.submit(() -> {
            	writeFile(current);
            });
		}

		
	}
	
	private static void writeFile(Integer index) {
		try {
			String currentTime = getCurrentDate();
			List<String> lines = Arrays.asList(currentTime);
			Path file = Paths.get("testFile"+index+".txt");
			Files.write(file, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String getCurrentDate() {
		DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime currentDateTime = LocalDateTime.now();
		return dataFormat.format(currentDateTime);
	}
	
	private static void dataBaseWrite() {
		return;
	}
}

