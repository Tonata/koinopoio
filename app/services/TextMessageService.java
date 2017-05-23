package services;

import domain.Resident;
import domain.TextMessage;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import repository.TextMessageRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by martian on 2017/05/04.
 */
public class TextMessageService {

    private TextMessageRepository repository;
    private Datastore datastore;
    private MapClasses mapping;
    private String status;

    public TextMessageService() {

        mapping     = new MapClasses();
        repository  = new TextMessageRepository(mapping.getClient(),mapping.mapArea(), mapping.getDbName());
        datastore   = mapping.getDatastore();
    }

    public Boolean createTextMessage(String service,
                                     String cutoffDate,
                                     String timeFrom,
                                     String timeTo,
                                     List<String> areaName,
                                     String smsApiUsername,
                                     String smsApiKey,
                                     String username){

        TextMessage message = new TextMessage(service, cutoffDate, timeFrom, timeTo, areaName);
        List<String> numbers = new ArrayList<>();


        for(int i = 0; i < areaName.size(); i++){
            Query<Resident> findQry = datastore.createQuery(Resident.class).field("areaName").equalIgnoreCase(areaName.get(i));

            List<Resident> residents = findQry.asList();


            for(int x = 0 ; x < residents.size(); x++){
                numbers.add(residents.get(x).getContactNumber());
            }
        }

        try{

            String user = "username=" + smsApiUsername;
            String hash = "&apiKey=" + smsApiKey;
            String textmessage = "&message=" + "Dear Resident, the " + service + " will be off on " + cutoffDate + " from " + timeFrom + " till " + timeTo + ". Sorry for the inconvenience.";
//            String sender = "&sender=" + "Koinopoio";
            String textNumbers = "&numbers=" + numbers.toString();

            HttpURLConnection conn = (HttpURLConnection) new URL("http://api.txtlocal.com/send/?").openConnection();
            String data = user + hash + textNumbers + textmessage;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));

            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            String json_respone = "";

            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
                json_respone += line;
            }
            rd.close();

            JSONObject jsonObject = new JSONObject(json_respone);

            status = jsonObject.getString("status");

        }catch(Exception e){
            System.out.print("Error sms " + e);

        }

        if (status.equals("success")){
            saveTextMessage(service, cutoffDate, timeFrom, timeTo, areaName, status, username);
            return true;
        }
        else{
            return false;
        }

    }

    public void saveTextMessage(String service,
                                String cutoffDate,
                                String timeFrom,
                                String timeTo,
                                List<String> areaName,
                                String status,
                                String username){
        // once test is sent save to db
        TextMessage message = new TextMessage(service,cutoffDate,timeFrom, timeTo,areaName);
        Object savedID = repository.save(message).getId();
        ObjectId retrievedID = new ObjectId(savedID.toString());

        TextLogService logService = new TextLogService();
        logService.createTextLog(retrievedID, new Date(), username, status );
    }

    public List<TextMessage> getAllMessages(){

        Query<TextMessage> textMessageQry = datastore.createQuery(TextMessage.class);
        return textMessageQry.asList();
    }

    public List<TextMessage> getAllMessageByCutoffDate(String cutoffDate){

        Query<TextMessage> textMessageQry = datastore.createQuery(TextMessage.class).field("cutoffDate").endsWithIgnoreCase(cutoffDate);
        return textMessageQry.asList();
    }

//    public List<TextMessage> getAllMessageByArea(String areaName){
//
//    }
}
