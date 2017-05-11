package repositoryTest;

import com.mongodb.MongoClient;
import conf.Connection;
import domain.Resident;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import repository.ResidentRepository;

import static org.testng.Assert.assertEquals;

/**
 * Created by martian on 2017/03/10.
 */
public class ResidentRepositoryTest  {

    private Connection dbConn = new Connection();
    private MongoClient client;
    private String dbName;
    private Morphia morphia;
    private ResidentRepository residentRepo;

    @BeforeTest
    public void setUp(){

        client = dbConn.getConnection();
        dbName = dbConn.getDatabaseString();
        morphia = new Morphia();

        morphia.map(Resident.class);
        residentRepo = new ResidentRepository(client, morphia, dbName);

    }

    @Test
    public void residentTest(){
        Resident residentOne = new Resident("264815706696", "T Nakashololo", "hkalenga@gmail.com", "Wanaheda");

        Object savedID = residentRepo.save(residentOne).getId();

        ObjectId retrievedID = new ObjectId(savedID.toString());

        assertEquals(residentRepo.get(retrievedID).getContactNumber(), "264815706696");

    }

    @AfterTest
    public void cleanUp(){
        //Free up memory
        //Threads
    }



}
