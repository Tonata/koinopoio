package domain;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import java.util.Date;

/**
 * Created by martian on 2017/02/25.
 */
@Entity(noClassnameStored = true, value = "textlog")
public class TextLog {
    @Id
    private ObjectId id;

    @Indexed(unique = false)
    private Date dateSent;

    @Indexed(unique = false)
    private DateTime timeSent;

    private String userID;
    private String status;
    private ObjectId textID;

    public TextLog() {
    }

    public TextLog(Date dateSent, DateTime timeSent, String userID, String status, ObjectId textID) {
        this.dateSent = dateSent;
        this.timeSent = timeSent;
        this.userID = userID;
        this.status = status;
        this.textID = textID;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public DateTime getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(DateTime timeSent) {
        this.timeSent = timeSent;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ObjectId getTextID() {
        return textID;
    }

    public void setTextID(ObjectId textID) {
        this.textID = textID;
    }
}
