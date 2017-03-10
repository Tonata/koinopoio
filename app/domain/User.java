package domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

/**
 * Created by martian on 2017/02/25.
 */
@Entity(noClassnameStored = true, value = "user")
public class User {
    @Id
    private ObjectId userID;

    @Indexed(unique = false)
    private String username;

    private String password;
    private ObjectId personID;

    public User() {
    }

    public User(String username, String password, ObjectId personID) {
        this.username = username;
        this.password = password;
        this.personID = personID;
    }

    public ObjectId getUserID() {
        return userID;
    }

    public void setUserID(ObjectId userID) {
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

    public ObjectId getPersonID() {
        return personID;
    }

    public void setPersonID(ObjectId personID) {
        this.personID = personID;
    }
}
