package services;

import domain.TextLog;
import domain.TextMessage;
import domain.User;
import org.mongodb.morphia.Datastore;
import repository.TextLogRepository;

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

    public void createTextLog(TextMessage textMessage){

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
