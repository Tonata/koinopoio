package repository;

import com.mongodb.MongoClient;
import domain.Resident;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by martian on 2017/03/09.
 */
public class ResidentRepository extends BasicDAO<Resident, ObjectId> {

    public ResidentRepository(MongoClient client, Morphia morphia, String dbname){
        super(client, morphia, dbname);
    }

    public ResidentRepository (Class<Resident> entityClass, MongoClient client, Morphia morphia, String dbname){
        super (entityClass, client, morphia, dbname);
    }

    public ResidentRepository(Class<Resident> entityClass, Datastore datastore){
        super(entityClass, datastore);
    }

    public ResidentRepository( Datastore datastore){
        super(datastore);
    }
}
