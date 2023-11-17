import java.sql.*;

public class User {
    protected String first_name;
    protected String last_name;
    private String password;
    private String email;
    protected String nickname;
    protected String b_date;
    protected String profession;
    protected String gender;
    protected String country;
    protected String choice;

    public User(String first_name, String last_name, String nickname, String b_date, String profession, String gender,
            String country, String choice) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.nickname = nickname;
        this.b_date = b_date;
        this.gender = gender;
        this.country = country;
        this.profession = profession;
        this.choice = choice;

    }

    public String getFirstName() {
        return this.first_name;
    }

    public String getLastName() {
        return this.last_name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getBirthDate() {
        return this.b_date;
    }

    public String getProfession() {
        return this.profession;
    }

    public String getGender() {
        return this.gender;
    }

    public String getCountry() {
        return this.country;
    }

    public String getChoice() {
        return this.choice;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public void setNickName(String nickname) {
        this.nickname = nickname;
    }

    public void setBirthdate(String b_date) {
        this.b_date = b_date;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean databaseInsert() {

        System.out.println(this.gender);
        // checking if the email format is correct
        if (this.email.contains("@") == false && this.email.contains(".") == false) {
            System.out.println("Incorrect Email Format");
            return false;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/pfbaliwis", "root", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS User (email varchar(30) PRIMARY KEY NOT NULL, f_name varchar(30) NOT NULL,  l_name varchar(30) NOT NULL, password varchar(30) NOT NULL, nickname varchar(15) NOT NULL, bdate date NOT NULL, gender ENUM('Male', 'Female', 'Others'), profession ENUM('Student', 'Working', 'Unemployed', 'Retired'), country varchar(30) NOT NULL, choice ENUM('EventManager', 'Attendee'))");
            System.out.println("Created table in given database...");

            String sql = "INSERT INTO User(email, f_name, l_name, password, nickname, bdate, gender, profession, country, choice)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ? ,?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, this.email);
            pstmt.setString(2, this.first_name);
            pstmt.setString(3, this.last_name);
            pstmt.setString(4, this.password);
            pstmt.setString(5, this.nickname);
            pstmt.setString(6, this.b_date);
            pstmt.setString(7, this.gender);
            pstmt.setString(8, this.profession);
            pstmt.setString(9, this.country);
            pstmt.setString(10, this.choice);

            pstmt.executeUpdate();
            // System.out.println(id + f_name + l_name + password + nickname + bdate +
            // gender);
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
