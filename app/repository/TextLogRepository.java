package repository;

import com.mongodb.MongoClient;
import domain.Area;
import domain.TextLog;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by martian on 2017/03/09.
 */
public class TextLogRepository extends BasicDAO<TextLog, ObjectId> {

    public TextLogRepository(MongoClient client, Morphia morphia, String dbname){
        super(client, morphia, dbname);
    }

    public TextLogRepository (Class<TextLog> entityClass, MongoClient client, Morphia morphia, String dbname){
        super (entityClass, client, morphia, dbname);
    }

    public TextLogRepository(Class<TextLog> entityClass, Datastore datastore){
        super(entityClass, datastore);
    }
}
