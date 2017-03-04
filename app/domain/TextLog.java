package domain;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by martian on 2017/02/25.
 */
public class TextLog {

    private Date dateSent;
    private DateTime timeSent;
    private String userID;
    private String status;
    private String textID;

    private TextLog (Builder b){
        dateSent    = b.dateSent;
        timeSent    = b.timeSent;
        userID      = b.userID;
        status      = b.status;
        textID      = b.textID;
    }

    public static class Builder{
        private Date dateSent;
        private DateTime timeSent;
        private String userID;
        private String status;
        private String textID;

        public Builder(){

        }

        public Builder dateSent(Date sent){
            dateSent = sent;
            return this;
        }

        public Builder timeSent (DateTime tSent){
            timeSent = tSent;
            return this;
        }

        public Builder userID(String uID){
            userID = uID;
            return this;
        }

        public Builder status (String stat){
            status = stat;
            return this;
        }

        public Builder textID (String tID){
            textID = tID;
            return this;
        }

        public Builder textLog(TextLog log){
            dateSent    = log.getDateSent();
            timeSent    = log.getTimeSent();
            userID      = log.getUserID();
            status      = log.getStatus();
            textID      = log.getTextID();
            return this;
        }

        public TextLog build(){
            return new TextLog(this);
        }
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

    public String getTextID() {
        return textID;
    }

    public void setTextID(String textID) {
        this.textID = textID;
    }
}
