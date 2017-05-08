package services;

import com.mongodb.MongoClient;
import conf.Connection;
import domain.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.Set;

/**
 * Created by martian on 2017/03/09.
 */
public class MapClasses {

    private Connection dbConn = new Connection();
    private MongoClient client;
    private String dbName;
    private Datastore datastore;
    private Morphia morphia;

    public MapClasses() {

        client            = dbConn.getConnection();
        dbName            = dbConn.getDatabaseString();
        morphia           = new Morphia();

    }

    public Connection getDbConn() {
        return dbConn;
    }

    public MongoClient getClient() {
        return client;
    }

    public String getDbName() {
        return dbName;
    }

    public Morphia getMorphia() {
        return morphia;
    }

    public void mapAllClass(){

        morphia.mapPackage("domain");

//        Set<Object> classes = new Set<Object>() {
//        }
    }

    public Datastore getDatastore(){
        datastore = morphia.createDatastore(client, dbName);
        return datastore;
    }

    public Morphia mapArea(){
        morphia.map(Area.class);
        return morphia;
    }

    public void mapResident(){
        morphia.map(Resident.class);
    }

    public void mapPerson(){
        morphia.map(Person.class);
    }

    public void mapUser(){
        morphia.map(User.class);
    }

    public void mapTextMessage(){
        morphia.map(TextMessage.class);
    }

    public void mapTextLog(){
        morphia.map(TextLog.class);
    }

}
