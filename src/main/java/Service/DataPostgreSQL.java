package Service;

public class DataPostgreSQL {
    // private String url = "jdbc:postgresql://localhost:5432/Human_friends";
    private String url = "jdbc:postgresql://192.168.1.6:5433/Human_friends";
    private String user = "postgres";
    private String password = "postgres";

    public DataPostgreSQL() {}

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
