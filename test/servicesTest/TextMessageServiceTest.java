package servicesTest;

import com.mongodb.MongoClient;
import conf.Connection;
import domain.Service;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import services.TextMessageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by martian on 2017/05/11.
 */
public class TextMessageServiceTest {

    private Connection dbConn = new Connection();
    private MongoClient client;
    private String dbName;
    private TextMessageService service;
    private Datastore datastore;
    private Morphia morphiaTwo;
    @BeforeTest
    public void setUp(){

        client            = dbConn.getConnection();
        dbName            = dbConn.getDatabaseString();
        morphiaTwo        = new Morphia();
        datastore = morphiaTwo.createDatastore(client,dbName);

        service = new TextMessageService();

    }

    @Test
    public void textMessage(){

        List<String> areaName = new ArrayList<>();

        areaName.add("Wanaheda");
        areaName.add("Olympia");

        service.createTextMessage(Service.ELECTRICITY.toString(),
                "12 May 2017",
                "8:30",
                "15:30",
                areaName,
                "tonata93@gmail.com",
                "v+bQR+C9rWo-srUjpOzDuBhIYWEZGq2LzpPiv3XO6Y",
                "tNak");

    }

    @AfterTest
    public void cleanUp(){

    }
}
