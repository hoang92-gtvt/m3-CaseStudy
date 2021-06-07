package model;

public class User {

    private int id;
    private String name;
    private String birthday;
    private String email;
    private String phone;
    private String urlOfImg;
    private String userName;
    private String pass;
    private Role role;

    public User() {
    }

    public User(int id, String name, String birthday, String email, String phone, String urlOfImg, String userName, String pass, Role role) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.urlOfImg = urlOfImg;
        this.userName = userName;
        this.pass = pass;
        this.role = role;
    }
    public User(int id, String name, String birthday, String email, String phone, String urlOfImg, Role role) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.urlOfImg = urlOfImg;
        this.role = role;
    }

    public User(String name, String birthday, String email, String phone, String urlOfImg, String userName, String pass, Role role) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.urlOfImg = urlOfImg;
        this.userName = userName;
        this.pass = pass;
        this.role = role;
    }

    public User(String name, String birthday, String email, String phone, String userName, String pass, Role role) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.userName = userName;
        this.pass = pass;
        this.role = role;
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
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getUrlOfImg() {
        return urlOfImg;
    }
    public void setUrlOfImg(String urlOfImg) {
        this.urlOfImg = urlOfImg;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

}
