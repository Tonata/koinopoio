package domain;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

/**
 * Created by martian on 2017/02/25.
 */
public class TextMessage {

    private String textID;
    private List<String> service;
    private Date cutoffDate;
    private DateTime timeFrom;
    private DateTime timeTo;
    private List<String> areaName;

    private TextMessage (Builder b){
        textID      = b.textID;
        service     = b.service;
        cutoffDate  = b.cutoffDate;
        timeFrom    = b.timeFrom;
        timeTo      = b.timeTo;
        areaName    = b.areaName;
    }

    public static class Builder{
        private String textID;
        private List<String> service;
        private Date cutoffDate;
        private DateTime timeFrom;
        private DateTime timeTo;
        private List<String> areaName;

        public Builder(){

        }

        public Builder textID (String tID){
            textID = tID;
            return this;
        }

        public Builder service (List<String> s){
            service = s;
            return this;
        }

        public Builder cutoffDate (Date cDate){
            cutoffDate = cDate;
            return this;
        }

        public Builder timeFrom (DateTime tFrom){
            timeFrom = tFrom;
            return this;
        }

        public Builder timeTo (DateTime tTo){
            timeTo = tTo;
            return this;
        }

        public Builder areaName (List<String> name){
            areaName = name;
            return this;
        }

        public Builder textMessage (TextMessage msg){
            textID          = msg.getTextID();
            service         = msg.getService();
            cutoffDate      = msg.getCutoffDate();
            timeFrom        = msg.getTimeFrom();
            timeTo          = msg.getTimeTo();
            areaName        = msg.getAreaName();
            return this;
        }

        public TextMessage build(){
            return new TextMessage(this);
        }
    }

    public String getTextID() {
        return textID;
    }

    public void setTextID(String textID) {
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

    public DateTime getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(DateTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public DateTime getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(DateTime timeTo) {
        this.timeTo = timeTo;
    }

    public List<String> getAreaName() {
        return areaName;
    }

    public void setAreaName(List<String> areaName) {
        this.areaName = areaName;
    }
}
