package domain;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by martian on 2017/02/25.
 */
@Entity(noClassnameStored = true, value = "textlog")
public class TextLog {
    @Id
    private ObjectId id;

    @Indexed(unique = false)
    private Date dateTimeSent;

    @Reference
    private User user;

    @Reference
    private TextMessage text;

    private String status;

    public TextLog() {
    }

    public TextLog(Date dateTimeSent, User user, String status, TextMessage text) {
        this.dateTimeSent = dateTimeSent;
        this.user = user;
        this.status = status;
        this.text = text;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getDateTimeSent() {
        return dateTimeSent;
    }

    public void setDateTimeSent(Date dateSent) {
        this.dateTimeSent = dateTimeSent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user= user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TextMessage getText() {
        return text;
    }

    public void setText(TextMessage text) {
        this.text = text;
    }
}
