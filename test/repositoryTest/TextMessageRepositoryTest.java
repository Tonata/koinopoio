package repositoryTest;

import com.mongodb.MongoClient;
import conf.Connection;
import domain.Service;
import domain.TextMessage;
import org.bson.types.ObjectId;
import org.joda.time.LocalTime;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.converters.LocalTimeConverter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import repository.TextMessageRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");

        Calendar dt = new GregorianCalendar(2017,3,19);

//        dt.set(2017,3,19);


        System.out.println(sdf.format(dt.getTime()));

//        List<String> areaNames = new ArrayList<String>();
//
//        areaNames.add("Wanaheda");
//
//        areaNames.add("WHK North");
//
//        TextMessage msg1 = new TextMessage(  Service.WATER.toString(), new Date(), 10.30, 20.30, areaNames);
//        Object savedID = textMsgRepo.save(msg1).getId();
//
//        ObjectId retrievedID = new ObjectId(savedID.toString());
//
//        assertEquals(textMsgRepo.get(retrievedID).getService().contains("WATER"), true);


    }

    @AfterTest
    public void cleanUp(){
        //Free up memory
        //Threads
    }
}
