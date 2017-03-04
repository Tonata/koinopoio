package domain;

/**
 * Created by martian on 2017/02/25.
 */
public class User {

    private String userID;
    private String username;
    private String password;
    private String personID;

    private User (Builder b){
        userID = b.userID;
        username = b.username;
        password = b.password;
        personID = b.personID;
    }

    public static class Builder{
        private String userID;
        private String username;
        private String password;
        private String personID;

        public Builder(){

        }

        public Builder userID (String uID){
            userID = uID;
            return this;
        }

        public Builder username (String uName){
            username = uName;
            return this;
        }

        public Builder password (String pWord){
            password = pWord;
            return this;
        }

        public Builder personID (String pID){
            personID = pID;
            return this;
        }

        public Builder user (User u){
            userID      = u.getUserID();
            username    = u.getUsername();
            password    = u.getPassword();
            personID    = u.getPersonID();
            return this;
        }

        public User build(){
            return new User(this);
        }
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
