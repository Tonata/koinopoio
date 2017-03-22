package repositoryTest;

import com.mongodb.MongoClient;
import conf.Connection;
import domain.Person;
import domain.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import repository.PersonRepository;
import repository.UserRepository;
import services.UsernameGenerator;

/**
 * Created by martian on 2017/03/20.
 */
public class UserRepositoryTest {

    private Connection          dbConn = new Connection();
    private MongoClient         client;
    private String              dbName;
    private Morphia             morphia;
    private Morphia             morphiaTwo;
    private UserRepository      userRepo;
    private PersonRepository    personRepo;
    private Datastore           datastore;
    private UsernameGenerator   usernameGenerator;

    @BeforeTest
    public void setUp(){

        client            = dbConn.getConnection();
        dbName            = dbConn.getDatabaseString();
        morphia           = new Morphia();
        morphiaTwo        = new Morphia();
        usernameGenerator = new UsernameGenerator();

        morphia.map(User.class);
        datastore = morphiaTwo.createDatastore(client,dbName);

        userRepo    = new UserRepository(client, morphia, dbName);
        personRepo  = new PersonRepository(Person.class, datastore);

    }

    @Test
    public void userTest(){

        Query<Person> personQry = datastore.createQuery(Person.class).field("firstName").equal("Jesse").field("lastName").equal("Schiceya");

        Person personOne = (Person) personQry.asList().get(0);

        String uName = usernameGenerator.generate("Jesse", "Schiceya");

        User userOne = new User(uName, "00000", personOne ,"admin");

        userRepo.save(userOne);

        //Needs asserts statement


    }

    @AfterTest
    public void cleanUp(){
        //Free up memory
        //Threads
    }
}
