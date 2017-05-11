package repositoryTest;

import com.mongodb.MongoClient;
import conf.Connection;
import domain.Area;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import org.mongodb.morphia.query.Query;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import repository.AreaRepository;

import static org.testng.Assert.assertEquals;

/**
 * Created by martian on 2017/03/09.
 */
public class AreaRepositoryTest {

    private Connection dbConn = new Connection();
    private MongoClient client;
    private String dbName;
    private Morphia morphia;
    private AreaRepository areaRepo;
    Datastore datastore;


    @BeforeTest
    public void setUp(){

        client = dbConn.getConnection();
        dbName = dbConn.getDatabaseString();
        morphia = new Morphia();

        morphia.map(Area.class);
        areaRepo = new AreaRepository(client, morphia, dbName);
        datastore = morphia.createDatastore(client,dbName);

    }

    @Test
    public void areaTest(){

//        Area areaOne = new Area("Rocky Crest", "005", "W7");
//        Object savedID = areaRepo.save(areaOne).getId();
//
//        ObjectId retrievedID = new ObjectId(savedID.toString());
//
//        assertEquals(areaRepo.get(retrievedID).getName(), "Rocky Crest");

        Query<Area> findQry = datastore.createQuery(Area.class).field("name").containsIgnoreCase("Katutura");
        System.out.println(findQry.asList().isEmpty());


//        String desktopPath = System.getProperty("user.home") + "/Desktop";
//        System.out.println(desktopPath.replace("\\", "/"));


//        Query<Area> areaQry = datastore.createQuery(Area.class);
//        System.out.println(areaQry.count());
    }

    @AfterTest
    public void cleanUp(){
        //Free up memory
        //Threads
    }
}
