package conf;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.casbah.MongoDB;
import com.mongodb.client.MongoDatabase;
import org.jongo.Jongo;

/**
 * Created by martian on 2017/02/25.
 */
public class Connection implements Config {

    public Connection(){

    }

    private static MongoClient client = null;

    public MongoClient getConnection(){

        String hosts = Config.config.getString("mongodb.host");
//        String database = Config.config.getString("mongodb.database");
        MongoClientURI uri = new MongoClientURI(hosts);
        if (client == null){
            client = new MongoClient(uri);
        }
        return client;
    }

    public MongoDatabase getDatabase(MongoClient client){
        String database = Config.config.getString("mongodb.database");

        return client.getDatabase(database);
    }

    public String getDatabaseString(){
        String database = Config.config.getString("mongodb.database");
        return database;
    }
}
