import java.sql.*;

public class User {
    protected  String first_name;
    protected  String last_name;
    private int id;
    private String password;

    public User(String first_name, String last_name){
       this.first_name = first_name;
       this.last_name = last_name;

      
}

    public int getID(){
        return id;
    }

    public String getPassword(){
        return password;
    }

    public void setID(int id){
        this.id = id;
    }

     public void setPassword(String password){
        this.password = password;
        
    }

    public void databaseInsert(String f_name, String l_name, int id, String password){

         try{  
        Class.forName("com.mysql.cj.jdbc.Driver");  
        
        Connection con=DriverManager.getConnection(  
        "jdbc:mysql://localhost/pfbaliwis","root","");  
        Statement stmt=con.createStatement();  
         stmt.executeUpdate("CREATE TABLE IF NOT EXISTS User (school_id int(11) PRIMARY KEY NOT NULL, f_name varchar(30) NOT NULL,  l_name varchar(30) NOT NULL, password varchar(30) NOT NULL)");
         System.out.println("Created table in given database...");  
        
         String sql = "INSERT INTO User(school_id, f_name, l_name, password)" + "VALUES(?, ?, ?, ?)";
         PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
         pstmt.setInt(1, id);
         pstmt.setString(2, f_name);
         pstmt.setString(3, l_name);
         pstmt.setString(4, password);
         pstmt.executeUpdate();
         System.out.println(id + f_name + l_name + password);
        con.close();  
        }catch(Exception e){ 
            System.out.println(e);
        }  
        }  

     /*    public boolean checkCredentials(int id, String password){

            boolean check = true;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");  
        
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost/pfbaliwis","root","");  
                //Statement stmt=con.createStatement(); 
                PreparedStatement pStatement = con.prepareStatement("SELECT school_id, password FROM User WHERE id == ? AND password == ?");
                pStatement.setInt(1, id);
                pStatement.setString(2, password);

                ResultSet rs = pStatement.executeQuery();

                if(rs.next() == true){
                    System.out.println("Valid");
                }
                else{
                    System.out.println("Invalid");
                }

                /*while(rs.next()){
                    if(rs.getInt("school_id") == id || rs.getString("password") == password){
                        check = true;
                    }
                    else if(rs.getInt("school_id") != id || rs.getString("password") != password);
                    check = false;
                }
            }
            catch(Exception e){ 
            System.out.println(e);

        }  

        return check;
    }*/

}



