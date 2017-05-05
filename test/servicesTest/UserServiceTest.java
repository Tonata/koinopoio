package servicesTest;

import com.mongodb.MongoClient;
import conf.Connection;
import domain.Person;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import services.UserService;

/**
 * Created by martian on 2017/05/06.
 */
public class UserServiceTest {

    private Connection dbConn = new Connection();
    private MongoClient client;
    private String dbName;
    private UserService service;
    private Datastore datastore;
    private Morphia             morphiaTwo;

    @BeforeTest
    public void setUp(){
        client            = dbConn.getConnection();
        dbName            = dbConn.getDatabaseString();
        morphiaTwo        = new Morphia();
        datastore = morphiaTwo.createDatastore(client,dbName);
    }

    @Test
    public void userServiceTest(){

        Query<Person> personQry = datastore.createQuery(Person.class).field("firstName").equal("Jane").field("lastName").equal("Doe");

        Person personOne = (Person) personQry.asList().get(0);

        service = new UserService();

        service.registerUser(personOne, "converse");
    }

    @AfterTest
    public void cleanUp(){}

}
