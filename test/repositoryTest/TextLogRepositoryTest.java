package repositoryTest;

import com.mongodb.MongoClient;
import conf.Connection;
import domain.Area;
import domain.TextLog;
import domain.TextMessage;
import domain.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import repository.AreaRepository;
import repository.TextLogRepository;
import repository.TextMessageRepository;
import repository.UserRepository;

import java.util.Date;

import static org.testng.Assert.assertEquals;

/**
 * Created by martian on 2017/03/22.
 */
public class TextLogRepositoryTest {

    private Connection dbConn = new Connection();
    private MongoClient client;
    private String dbName;
    private Morphia morphia;
    private Morphia genericMorphia;
    private TextLogRepository textLogRepo;
    private Datastore datastore;
//    private UserRepository userRepo;
//    private TextMessageRepository txtMsgRepo;

    @BeforeTest
    public void setUp(){

        client = dbConn.getConnection();
        dbName = dbConn.getDatabaseString();
        morphia = new Morphia();
        genericMorphia = new Morphia();

        datastore = genericMorphia.createDatastore(client, dbName);

        morphia.map(Area.class);
        textLogRepo = new TextLogRepository(client, morphia, dbName);
//        txtMsgRepo = new TextMessageRepository(TextMessage.class, datastore);
//        userRepo = new UserRepository(User.class, datastore);
    }

    @Test
    public void textLogTest(){

        Query<User> userQuery = datastore.createQuery(User.class).field("username").equal("tNak");

        Query<TextMessage> msgQry = datastore.createQuery(TextMessage.class).field("areaName").contains("Wanaheda");

        User userOne = (User) userQuery.asList().get(0);
        TextMessage textMessage = (TextMessage) msgQry.asList().get(0);

        TextLog logOne = new TextLog(new Date(), userOne, "sent", textMessage);

        Object savedID = textLogRepo.save(logOne).getId();

        ObjectId retrievedID = new ObjectId(savedID.toString());

//        assertEquals(textLogRepo.get(retrievedID).getTimeSent(), 17.00);

    }

    @AfterTest
    public void cleanUp(){
        //Free up memory
        //Threads
    }

}
