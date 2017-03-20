package domain;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

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
    private List<String> service;

    private Date cutoffDate;
    private Double timeFrom;
    private Double timeTo;
    private List<String> areaName;

    public TextMessage() {
    }

    public TextMessage(List<String> service, Date cutoffDate, Double timeFrom, Double timeTo, List<String> areaName) {
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

    public List<String> getService() {
        return service;
    }

    public void setService(List<String> service) {
        this.service = service;
    }

    public Date getCutoffDate() {
        return cutoffDate;
    }

    public void setCutoffDate(Date cutoffDate) {
        this.cutoffDate = cutoffDate;
    }

    public Double getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Double timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Double getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Double timeTo) {
        this.timeTo = timeTo;
    }

    public List<String> getAreaName() {
        return areaName;
    }

    public void setAreaName(List<String> areaName) {
        this.areaName = areaName;
    }
}
