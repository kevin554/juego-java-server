package dto;

public class Relacion {

    private int id;
    private String userName;
    private String friendUserName;

    public Relacion() {
    }

    public Relacion(int id, String userName, String friendUserName) {
        this.id = id;
        this.userName = userName;
        this.friendUserName = friendUserName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFriendUserName() {
        return friendUserName;
    }

    public void setFriendUserName(String friendUserName) {
        this.friendUserName = friendUserName;
    }

    @Override
    public String toString() {
        return "Relacion{" + "id=" + id + ", userName=" + userName + ", friendUserName=" + friendUserName + '}';
    }

}