package domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;

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
    private String role;

    @Reference
    private Person person;

    public User() {
    }

    public User(String username, String password, Person person, String role) {
        this.username = username;
        this.password = password;
        this.person = person;
        this.role     = role;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person personID) {
        this.person = personID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
