package com.employee;

import java.util.Scanner;

public class EmployeeMain {

	static Scanner input= new Scanner(System. in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ReadMySQL ms = new ReadMySQL();
		ms.StartmySQLconnection();
		int number = startingpage();
		while (number != 3) {
			if (number == 1){
				ms.ReadQuerey("select * from employee");
				number = startingpage();
				
			}else if (number==2){
				System.out.println("press 3 for inserting a row in Employee table \n"
						+ "press 4 for inserting a row in Department table \n "
						+ "press 5 for deleting a row"
						);
				number = input.nextInt();
				switch (number){
				
					case 3:
						
				    EmployeeTable e = new EmployeeTable ("srishti", 6 , 4, 35 , 10000);
					ms.InsertEmployeeRecord("insert into employee ( emp_name,emp_id, emp_age, dept_id) values (?,?,?,?)", e);
					break;
					
					case 5:
						ms.deleteRecord("delete from employee where emp_id = ?",6);
						break;
						//case 5:
					
				}
				
				number = startingpage();
			}else {
				System.out.println("Bye");
			}
		}
		System.out.println("successfully completed");
		if(!input.hasNext()) {
			input.close();
		}

	}

public static int startingpage() {
	System.out.println("Welcome to Employee Main page! \nYou can do following operations!"
			+ " \n press 1 to read Employee record  \n press 2 for Admin access \n");
	return input.nextInt();
	
}

}
