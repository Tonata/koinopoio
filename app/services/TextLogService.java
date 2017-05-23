package services;

import domain.TextLog;
import domain.TextMessage;
import domain.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import repository.TextLogRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by martian on 2017/05/04.
 */
public class TextLogService {

    private TextLogRepository repository;
    private Datastore datastore;
    private MapClasses mapping;


    public TextLogService() {

        mapping     = new MapClasses();
        repository  = new TextLogRepository(mapping.getClient(),mapping.mapArea(), mapping.getDbName());
        datastore   = mapping.getDatastore();
    }

    public void createTextLog(ObjectId textID, Date dateTimeSent, String user, String status){

       //Reference user and textmessage

        Query<User> userQry = datastore.createQuery(User.class).field("username").equal(user);

        User retrievedUser = (User) userQry.asList().get(0);

        Query<TextMessage> messageQry = datastore.createQuery(TextMessage.class).field("_id").equal(textID);

        TextMessage message = (TextMessage) messageQry.asList().get(0);

        TextLog textLog = new TextLog(dateTimeSent, retrievedUser, status, message);
        repository.save(textLog);

    }

//    public TextLog getTextLogByTextMessage(TextMessage message){
//
//    }
//
//    public List<TextLog> getTextLogByDate(Date dateSent){}
//
//    public List<TextLog> getTextLogByUser(User user){}
//
//    public List<TextLog> getTextLogByStatus(String status){}

}
