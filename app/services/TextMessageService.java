package services;

import domain.Resident;
import domain.TextMessage;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import repository.TextMessageRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by martian on 2017/05/04.
 */
public class TextMessageService {

    private TextMessageRepository repository;
    private Datastore datastore;
    private MapClasses mapping;

    public TextMessageService() {

        mapping     = new MapClasses();
        repository  = new TextMessageRepository(mapping.getClient(),mapping.mapArea(), mapping.getDbName());
        datastore   = mapping.getDatastore();
    }

    public void createTextMessage(List<String> service, Date cutoffDate, Double timeFrom, Double timeTo, List<String> areaName){

        TextMessage message = new TextMessage(service, cutoffDate, timeFrom, timeTo, areaName);
        List<String> numbers = new ArrayList<>();

        for(int i = 0; i < areaName.size(); i++){
            Query<Resident> findQry = datastore.createQuery(Resident.class).field("areaName").equalIgnoreCase(areaName);

            List<Resident> residents = findQry.asList();

            for(int x = 0 ; x < residents.size(); x++){
                numbers.add(residents.get(x).getContactNumber());
            }
        }

        try{

            String user = "username=" + "tonata93@gmail.com";
            String hash = "&hash=" + "v+bQR+C9rWo-srUjpOzDuBhIYWEZGq2LzpPiv3XO6Y";
            String textmessage = "&message=" + "Dear Resident, the " + service + " will be off on " + cutoffDate + "from " + timeFrom + " to " + timeTo;
//            String sender = "&sender=" + "Koinopoio";
            String textNumbers = "&numbers=" + numbers;

            HttpURLConnection conn = (HttpURLConnection) new URL("http://api.txtlocal.com/send/?").openConnection();
            String data = user + hash + textNumbers + textmessage;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

        }catch(Exception e){
            System.out.print("Error sms " + e);

        }

    }

    public Boolean sendTextMessage(){
        // once test is sent save to db
        return false;
    }

//    public List<TextMessage> getAllMessage(){
//
//    }
//
//    public List<TextMessage> getAllMessageByCutoffDate(Date cutoffDate){
//
//    }
//
//    public List<TextMessage> getAllMessageByArea(String areaName){
//
//    }
}
