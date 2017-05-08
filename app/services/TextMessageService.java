package services;

import domain.TextMessage;
import org.mongodb.morphia.Datastore;
import repository.TextMessageRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by martian on 2017/05/04.
 */
public class TextMessageService {

    private TextMessageRepository repository;
    private Datastore datastore;
    private MapClasses mapping;

    public TextMessageService() {

        mapping     = new MapClasses();
        repository  = new TextMessageRepository(mapping.getClient(),mapping.mapArea(), mapping.getDbName());
        datastore   = mapping.getDatastore();
    }

    public void createTextMessage(){

    }

    public Boolean sendTextMessage(){
        // once test is sent save to db
        return false;
    }

//    public List<TextMessage> getAllMessage(){
//
//    }
//
//    public List<TextMessage> getAllMessageByCutoffDate(Date cutoffDate){
//
//    }
//
//    public List<TextMessage> getAllMessageByArea(String areaName){
//
//    }
}
