package services;

import com.mongodb.MongoClient;
import conf.Connection;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.Set;

/**
 * Created by martian on 2017/03/09.
 */
public class CreateCollections {

    private Connection dbConn = new Connection();
    private MongoClient client;
    private String dbName;
    private Datastore datastore;
    private Morphia morphia;

    public CreateCollections() {

        client            = dbConn.getConnection();
        dbName            = dbConn.getDatabaseString();
        morphia           = new Morphia();


    }

    public void mapAllClass(){

        morphia.mapPackage("domain");

//        Set<Object> classes = new Set<Object>() {
//        }
    }




}
