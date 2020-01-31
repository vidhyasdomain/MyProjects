package com.employee;
import java.sql.*;
public class ReadMySQL {
	public Connection con;
	public Statement st;
	
	public void StartmySQLconnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","av203465");
		st = con.createStatement();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return st;
	}
	
	public void ReadQuerey(String Querey) {
		try {

			ResultSet rs = st.executeQuery(Querey);
			ResultSetMetaData metadata = rs.getMetaData();
			
			int columnCount = metadata.getColumnCount();
			
			for(int i=1;i<=columnCount;i++) {
				System.out.print(metadata.getColumnLabel(i)+": ");
			}
			System.out.println("");	
			
			while(rs.next()){
				for(int i=1;i<=columnCount;i++) {
					System.out.print(rs.getString(i)+"  ");
				}
				System.out.println("");
			} 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL Exception for the Querey: " + Querey + e );
			e.printStackTrace();
		}
	}

	public void InsertEmployeeRecord(String Querey,EmployeeTable Employee) {
		try {

			//ResultSet rs = st.executeQuery(Querey);
			PreparedStatement preparedStmt = con.prepareStatement(Querey);
			//String query= insert into employee ( emp_name,emp_id, emp_age, dept_id) values (?,?,?,?)
		     
		      preparedStmt.setString (1, Employee.Name);
		      preparedStmt.setInt (2, Employee.id );
		      preparedStmt.setInt    (3, Employee.age);
		      preparedStmt.setInt    (4, Employee.dpt_id);
		      preparedStmt.setInt    (5, Employee.salary);
		      // execute the preparedstatement
		      preparedStmt.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL Exception for the Insert Querey: " + Querey + e );
			e.printStackTrace();
		}
	}

	public void InsertDepartmentRecord(String Querey,Department department) {
		try {

			//ResultSet rs = st.executeQuery(Querey);
			PreparedStatement preparedStmt = con.prepareStatement(Querey);
			//String query= insert into department (emp_id, emp_name, emp_age, dept_id) values (?,?);
		      preparedStmt.setInt (1, department.dept_id );
		      preparedStmt.setString (2, department.dept_name);
		     

		      // execute the preparedstatement
		      preparedStmt.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL Exception for the Insert Querey: " + Querey + e );
			e.printStackTrace();
		}
	}
	public void deleteRecord(String query, int primaryKey) {
		
		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);
			//String query = "delete from users where id = ?";
			preparedStmt.setInt(1, primaryKey);
			 preparedStmt.execute();
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL Exception for Delete Query" + e);
					}
			
	}
	
	
	public void ClosemySQLConnection() {
		try {
			st.close();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("unable to close the connection: " + e);
		}
			
	}
	
}
