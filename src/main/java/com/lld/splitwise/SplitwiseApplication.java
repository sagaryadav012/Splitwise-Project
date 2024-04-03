package com.lld.splitwise;

import com.lld.splitwise.commands.Command;
import com.lld.splitwise.commands.CommandRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("Awaited for input");
			String input = sc.nextLine();
			Command command = CommandRegistry.getInstance().getCommand(input);
			command.execute(input);
		}
	}
}
