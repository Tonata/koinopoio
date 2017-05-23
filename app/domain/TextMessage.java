package domain;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by martian on 2017/02/25.
 */
@Entity(noClassnameStored = true, value = "textmessage")
public class TextMessage {
    @Id
    private ObjectId textID;

    @Indexed(unique = false)
    private String service;

    private String cutoffDate;
    private String timeFrom;
    private String timeTo;
    private List<String> areaName;

    public TextMessage() {
    }

    public TextMessage(String service, String cutoffDate, String timeFrom, String timeTo, List<String> areaName) {
        this.service = service;
        this.cutoffDate = cutoffDate;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.areaName = areaName;
    }

    public ObjectId getTextID() {
        return textID;
    }

    public void setTextID(ObjectId textID) {
        this.textID = textID;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCutoffDate() {
        return cutoffDate;
    }

    public void setCutoffDate(String cutoffDate) {
        this.cutoffDate = cutoffDate;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public List<String> getAreaName() {
        return areaName;
    }

    public void setAreaName(List<String> areaName) {
        this.areaName = areaName;
    }
}
