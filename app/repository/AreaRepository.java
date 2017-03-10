package repository;

import com.mongodb.MongoClient;
import domain.Area;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by martian on 2017/03/09.
 */
public class AreaRepository extends BasicDAO<Area, ObjectId> {

    public AreaRepository(MongoClient client, Morphia morphia, String dbname){
        super(client, morphia, dbname);
    }

    public AreaRepository (Class<Area> entityClass, MongoClient client, Morphia morphia, String dbname){
        super (entityClass, client, morphia, dbname);
    }

    public AreaRepository(Class<Area> entityClass, Datastore datastore){
        super(entityClass, datastore);
    }

}
