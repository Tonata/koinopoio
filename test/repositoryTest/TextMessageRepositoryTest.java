package repositoryTest;

import com.mongodb.MongoClient;
import conf.Connection;
import domain.TextMessage;
import org.bson.types.ObjectId;
import org.joda.time.LocalTime;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.converters.LocalTimeConverter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import repository.TextMessageRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by martian on 2017/03/11.
 */
public class TextMessageRepositoryTest {

    private Connection dbConn = new Connection();
    private MongoClient client;
    private String dbName;
    private Morphia morphia;
    private TextMessageRepository textMsgRepo;

    @BeforeTest
    public void setUp(){

        client = dbConn.getConnection();
        dbName = dbConn.getDatabaseString();
        morphia = new Morphia();

        morphia.map(TextMessage.class);
        textMsgRepo = new TextMessageRepository(client, morphia, dbName);

    }

    @Test
    public void areaTest(){

        List<String> service = new ArrayList<String>();
        List<String> areaNames = new ArrayList<String>();

        service.add("Electricity");

        areaNames.add("Wanaheda");
        areaNames.add("Rocky Crest");
        areaNames.add("WHK North");

        TextMessage msg1 = new TextMessage(service, new Date(), 10.30, 20.30, areaNames);
        Object savedID = textMsgRepo.save(msg1).getId();

        ObjectId retrievedID = new ObjectId(savedID.toString());

        assertEquals(textMsgRepo.get(retrievedID).getService().contains("Electricity"), true);
    }

    @AfterTest
    public void cleanUp(){
        //Free up memory
        //Threads
    }
}
