package repository;

import com.mongodb.MongoClient;
import domain.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by martian on 2017/03/09.
 */
public class UserRepository extends BasicDAO<User, ObjectId> {

    public UserRepository(MongoClient client, Morphia morphia, String dbname){
        super(client, morphia, dbname);
    }

    public UserRepository (Class<User> entityClass, MongoClient client, Morphia morphia, String dbname){
        super (entityClass, client, morphia, dbname);
    }

    public UserRepository(Class<User> entityClass, Datastore datastore){
        super(entityClass, datastore);
    }
}
