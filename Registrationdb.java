import java.sql.*;

public class Registrationdb {
    protected int workshop_code;
    protected String manager_email;
    protected String title;
    protected String description;
    protected String date;
    protected String venue;
    protected String speakers;
    protected String host;
    protected String org;

    public Registrationdb(String manager_email, String title, String description, String date,
            String venue, String speakers, String host, String org) {
        this.manager_email = manager_email;
        this.title = title;
        this.description = description;
        this.date = date;
        this.venue = venue;
        this.speakers = speakers;
        this.host = host;
        this.org = org;
    }

    public void setCode(int code) {
        this.workshop_code = code;
    }

    public boolean databaseInsert() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/pfbaliwis", "root", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Workshop(ws_code int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL, email varchar(30) NOT NULL, title varchar(30) NOT NULL, description text(400), event_date date NOT NULL, venue varchar(50) NOT NULL, speakers varchar(100) NOT NULL, host varchar(30) NOT NULL, org varchar(100) NOT NULL, FOREIGN KEY(email) REFERENCES user(email) ON DELETE CASCADE )");
            System.out.println("Created table in given database...");

            String sql = "INSERT INTO workshop(email, title, description, event_date, venue, speakers, host, org)"
                    + "VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, this.manager_email);
            pstmt.setString(2, this.title);
            pstmt.setString(3, this.description);
            pstmt.setString(4, this.date);
            pstmt.setString(5, this.venue);
            pstmt.setString(6, this.speakers);
            pstmt.setString(7, this.host);
            pstmt.setString(8, this.org);
            pstmt.executeUpdate();
            System.out.println("VALUES INSERTED");

            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
