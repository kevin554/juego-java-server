package dto;

public class Usuario {

    private int id;
    private String name;
    private String username;
    private String password;
    private int record;
    private String token;

    public Usuario() {
        init();
    }

    public Usuario(int id, String name, String username, String password, int record, String token) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.record = record;
        this.token = token;
        init();
    }
    
    private void init() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", record=" + record + ", token=" + token + '}';
    }
    
}