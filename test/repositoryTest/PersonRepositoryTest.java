package repositoryTest;

import com.mongodb.MongoClient;
import conf.Connection;
import domain.Person;
import org.bson.types.ObjectId;
import org.junit.After;
import org.mongodb.morphia.Morphia;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import repository.PersonRepository;

import static org.testng.Assert.assertEquals;

/**
 * Created by martian on 2017/03/20.
 */
public class PersonRepositoryTest {

    private Connection dbConn = new Connection();
    private MongoClient client;
    private String dbName;
    private Morphia morphia;
    private PersonRepository personRepo;

    @BeforeTest
    public void setUp(){

        client = dbConn.getConnection();
        dbName = dbConn.getDatabaseString();
        morphia = new Morphia();

        morphia.map(Person.class);
        personRepo = new PersonRepository(client,morphia , dbName);
    }

    @Test
    public void personRepositoryTest(){

        Person personOne = new Person("Jane", "Doe", 'f');

        Object savedID = personRepo.save(personOne).getId();

        ObjectId retrievedID = new ObjectId(savedID.toString());

        assertEquals(personRepo.get(retrievedID).getGender(), 'm');

    }

    @AfterTest
    public void cleanUp(){
        //Free up memory
        //Threads
    }

}
