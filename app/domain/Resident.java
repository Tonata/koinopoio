package domain;

/**
 * Created by martian on 2017/02/25.
 */
public class Resident {

    private String residentID;
    private String name;
    private String contactNumber;
    private String emailAddress;
    private String areaID;

   private Resident(Builder b){
       residentID       = b.residentID;
       name             = b.name;
       contactNumber    = b.contactNumber;
       emailAddress     = b.emailAddress;
       areaID           = b.areaID;

   }

   public static class Builder{
       private String residentID;
       private String name;
       private String contactNumber;
       private String emailAddress;
       private String areaID;

       public Builder(){}

       public Builder residentID(String rID){
           residentID = rID;
           return this;
       }

       public Builder name (String n){
           name = n;
           return this;
       }

       public Builder contactNumber(String num){
           contactNumber = num;
           return this;
       }

       public Builder emailAddress(String email){
           emailAddress = email;
           return this;
       }

       public Builder areaID (String aID){
           areaID = aID;
           return this;
       }

       public Builder resident (Resident res){
           residentID       = res.getResidentID();
           name             = res.getName();
           contactNumber    = res.getContactNumber();
           emailAddress     = res.getEmailAddress();
           areaID           = res.getAreaID();
           return this;
       }

       public Resident build(){
           return new Resident(this);
       }
   }

    public String getResidentID() {
        return residentID;
    }

    public void setResidentID(String residentID) {
        this.residentID = residentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAreaID() {
        return areaID;
    }

    public void setAreaID(String areaID) {
        this.areaID = areaID;
    }
}
