package com.lld.splitwise;

import com.lld.splitwise.commands.Command;
import com.lld.splitwise.commands.CommandRegistry;
import com.lld.splitwise.controllers.SettleUpController;
import com.lld.splitwise.dtos.SettleGroupRequestDto;
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

/*
-> We take input from console, will pass it to CommandRegistry, There we get respective command object
   what command we pass in input, once we get command object, we check command format is correct or not,
   if not throw exception, else take data from input and set it to requestDto and pass it to respective
   controller to process request and send response back.
-> For example our request is register user, Our command is Register user(username, phoneNumber, password).
-> We take this input from console then pass it to controller through DTO objects, it will process request
   and send response through DTO object.
-> Controller do basic validation and will pass it to service, here we write code for request if we need any
   information from database then it will ask Repository, repository will fetch and pass it to service.
-> Service process request, and pass response to controller.
-> Here we have multiple commands to execute, So we use strategy design pattern. We have to execute all commands that
   we get from user. Execute method is common so extract it and put it in common interface. Now different commands
   will implement this method.
-> When we encounter a command we need respective command object, so use registry pattern to store objects of
   different commands.

Register Command :
-> Command "Register username, phoneNumber, password". We will take these input from user and will pass it to CommandRegistry,
   There we get respective command object what command we pass in input, once we get command object,
   we check command format is correct or not, if not throw exception, else take data from input and
   set it to requestDto and pass it to  UserController to process request and send response back.
-> Controller does basic validation and will pass to UserService.
-> Service will check if already user exists in dp with help of UserRepository, if exists throw exception
   else set data to User model and save it.
-> It saves object as well return user. So controller got user and set it to responseDto, will return responseDto.
-> RegisterCommand will get response dto, and prints if it gets success response else prints error message.



DB operations :

USE splitwise;

-- Register some users
Select * from user;

-- Create Groups
select * from group_s;
insert into group_s values(1, "Goa Trip", "GoneGoa"),
							(2, "Bankok Trip", "Bang Bankok");

-- Create GroupUsers
select * from group_s_users;
insert into group_s_users values(1, 1),
								(1, 2),
                                (1, 3),
                                (1, 4);

-- Create Expense
select * from expense;
insert into expense values(1, null, 2500, 1, "For Food", "url"),
							(2, null, 5000, 1, "For Tickets", "url");

-- Create GroupExpense
select * from group_expense;
insert into group_expense values(1, 1, 1),
								(2 , 2, 1);

-- Create ExpenseUser
select * from expense_user;
insert into expense_user values(1, 2000, 0, 1, 1),
								(2, 500, 0, 1, 3), -- Expense1 paid by userid(1,3)

                                (3, 2300, 0, 2, 1),
                                (4, 2700, 0, 2, 3), -- Expense2 paid by userid(1,3)

                                -- Expense1 owed by all users involved in groupid 1
                                (5, 625, 1, 1, 1),
                                (6, 625, 1, 1, 2),
                                (7, 625, 1, 1, 3),
                                (8, 625, 1, 1, 4),

                                -- Expense2 owed by all users involved in groupid 1
                                (9, 1250, 1, 2, 1),
                                (10, 1250, 1, 2, 2),
                                (11, 1250, 1, 2, 3),
                                (12, 1250, 1, 2, 4);



 */