package javaproject;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

class Book{

public void addBook(int b_id,String name,String author,int quantity_available)throws Exception
{
	String url="jdbc:mysql://localhost:3306/mydb";
	String userName="root";
	String passWord="asmithasql";
	String query="INSERT INTO book (b_id, name, author, q_avai) VALUES (?, ?, ?, ?)"; 
	
	Connection con=DriverManager.getConnection(url, userName, passWord);
	PreparedStatement st = con.prepareStatement(query);

	st.setInt(1, b_id);
	st.setString(2, name);
	st.setString(3, author);
	st.setInt(4, quantity_available);
	int rows=st.executeUpdate(query);
	System.out.println("Number of rows affected: "+rows);

	
	con.close();

	
}
public void display()throws Exception
{
	String url="jdbc:mysql://localhost:3306/mydb";
	String userName="root";
	String passWord="asmithasql";
	String query="select * from book"; 
	
	Connection con=DriverManager.getConnection(url,userName,passWord);
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery(query);
	while(rs.next()) {
		int b_id=rs.getInt("b_id");
	    String name=rs.getString("name");
	    String author=rs.getString("author");
	    int q_avai=rs.getInt("q_avai");
	    System.out.println("ID: " + b_id + ", Name: " + name + ", Author: " + author + ", Quantity Available: " + q_avai);
}
}
	
	
public void checkout(int b_id,int id,LocalDate checkout,LocalDate checkin) throws Exception
{
	String url="jdbc:mysql://localhost:3306/mydb";
	String userName="root";
	String passWord="asmithasql";
	String query="INSERT INTO transaction (b_id, id, checkout, checkin) VALUES (?, ?, ?, ?)"; 
	
	Connection con=DriverManager.getConnection(url, userName, passWord);
	PreparedStatement st = con.prepareStatement(query);

	st.setInt(1, b_id);
	st.setInt(2,id);
	st.setDate(3,java.sql.Date.valueOf(checkout));
	st.setDate(4,java.sql.Date.valueOf(checkin) );
//	int rows=st.executeUpdate();
//	System.out.println("Number of rows affected: "+rows);
	System.out.println(checkin);

	
	con.close();
	
}
	    
	

public void adduser(int id,String name)throws Exception
{
	String url="jdbc:mysql://localhost:3306/mydb";
	String userName="root";
	String passWord="asmithasql";
	String query="INSERT INTO user(id,name) VALUES (?, ?)"; 
	
	Connection con=DriverManager.getConnection(url, userName, passWord);
	PreparedStatement st = con.prepareStatement(query);

	st.setInt(1, id);
	st.setString(2, name);
	int rows=st.executeUpdate();
	System.out.println("Number of rows affected: "+rows);

	
	con.close();
	
}


}
public class project{
	public static void main(String args[])throws Exception
	{
		Scanner sc=new Scanner(System.in);
		boolean continueProgram = true;
		while(continueProgram)
		{
		System.out.println("welcome");
		System.out.println("Enter your choice");
		System.out.println("1.add new book");
		System.out.println("2.add new user");
		System.out.println("3.display all books available");
		System.out.println("4.book check-out");
		System.out.println("5.book check-in");
		int choice=sc.nextInt();
		Book b=new Book();
		switch(choice)
		{
		case 1:
			System.out.println("Enter the book id");
			int b_id=sc.nextInt();
			System.out.println("Enter the book name");
			String name=sc.next();
			System.out.println("Enter the author name");
			String author=sc.next();
			System.out.println("Enter the book id");
			int quantity_available=Integer.parseInt(sc.next());
			b.addBook(b_id,name,author,quantity_available);
			break;
		case 2:
			System.out.println("Enter the user id");
			int id=sc.nextInt();
			System.out.println("Enter the user name");
			String user_name=sc.next();
			b.adduser(id,user_name);
			break;
		case 3:
			b.display();
			break;
		
		case 4:
			System.out.println("Enter the book id");
			int bid=sc.nextInt();
			System.out.println("Enter the user id");
			int uid=sc.nextInt();
			LocalDate currentdate=LocalDate.now();
			LocalDate returndate=currentdate.plusDays(15);
			b.checkout(bid,uid,currentdate,returndate);
		case 6:
            // Exit the program
            System.out.println("Do you want to exit? (yes/no)");
            String exitChoice = sc.next().toLowerCase();
            if (exitChoice.equals("yes")) {
                continueProgram = false;
                System.out.println("Exiting program...");
            }
            break;
        default:
            System.out.println("Invalid choice. Please enter a number between 1 and 5.");
		}
			
			
		}
			
			
	}
}
