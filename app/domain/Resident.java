package domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

/**
 * Created by martian on 2017/02/25.
 */
@Entity(noClassnameStored = true, value = "resident")
public class Resident {
    @Id
    private ObjectId residentID;

    @Indexed(unique = false)
    private String contactNumber;
    @Indexed(unique = false)
    private String name;

    private String emailAddress;
    private String areaName;

    public Resident() {
    }

    public Resident(String contactNumber, String name, String emailAddress, String areaName) {
        this.contactNumber = contactNumber;
        this.name = name;
        this.emailAddress = emailAddress;
        this.areaName = areaName;
    }

    public ObjectId getResidentID() {
        return residentID;
    }

    public void setResidentID(ObjectId residentID) {
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
