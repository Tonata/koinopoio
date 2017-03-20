package repository;

import com.mongodb.MongoClient;
import domain.TextMessage;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by martian on 2017/03/09.
 */
public class TextMessageRepository extends BasicDAO<TextMessage, ObjectId> {

    public TextMessageRepository(MongoClient client, Morphia morphia, String dbname){
        super(client, morphia, dbname);
    }

    public TextMessageRepository (Class<TextMessage> entityClass, MongoClient client, Morphia morphia, String dbname){
        super (entityClass, client, morphia, dbname);
    }

    public TextMessageRepository(Class<TextMessage> entityClass, Datastore datastore){
        super(entityClass, datastore);
    }
}
