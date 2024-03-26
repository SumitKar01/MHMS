package Project;

public class Person {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String gender;
    private String dob;
    private String nid;
    private String bg;
    private String marital;
    private String occupation;
    private String username;
    private String password;
    
    Person(String name, String address, String phone, String email, String gender, String dob, String nid, String bg, String marital, String occupation, String username, String password){
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.nid= nid;
        this.bg = bg;
        this.marital = marital;
        this.occupation = occupation;
        this.username = username;
        this.password = password;
               
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getAddress(){
        return this.address;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getGender(){
        return this.gender;
    }
    public String getDOB(){
        return this.dob;
    }
    public String getNID(){
        return this.nid;
    }
    public String getBlood(){
        return this.bg;
    }
    public String getMarital(){
        return this.marital;
    }
    public String getOccupation(){
        return this.occupation;
    }
    public void setUsername(String uname){
        this.username = uname;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
}
   