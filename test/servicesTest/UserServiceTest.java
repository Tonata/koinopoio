package servicesTest;

import com.mongodb.MongoClient;
import conf.Connection;
import domain.Person;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import services.UserService;

import static org.testng.Assert.assertEquals;

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

        service = new UserService();
    }

    @Test
    public void userLoginTest(){

        Boolean auth = service.login("JDoe67", "komeho");

        Assert.assertTrue( auth);
    }

//    @Test
//    public void getUsernameTest(){
//
//        Person p = new Person("Jane", "Doe", 'f');
//        assertEquals(service.getUsername(p), "JDoe67");
//    }
//
//    @Test
//    public void changePasswordTest(){
//
//        service.changePassword("JDoe67", "komeho");
//    }


    @AfterTest
    public void cleanUp(){}

}
