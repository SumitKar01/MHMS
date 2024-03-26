package Project;

public class Doc extends Person{
    private String bmds;
    
    
    Doc(String name, String address, String phone, String email, String gender, String dob, String nid, String bg, String marital, String occupation, String username, String password, String bmds){
       super(name, address, phone, email, gender, dob, nid, bg, marital, occupation, username, password);
        this.bmds = bmds;
        
               
    }
    public String getBMDS(){
        return this.bmds;
    }
    
    public void setBMDS(String bmds){
        this.bmds = bmds;
    }
}
   