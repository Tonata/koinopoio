package services;

import com.mongodb.MongoClient;
import conf.Connection;
import domain.Area;
import domain.Person;
import domain.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import repository.AreaRepository;
import repository.UserRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by martian on 2017/05/01.
 */
public class UserService {

    UserRepository repository;
    Connection connection = new Connection();
    Datastore datastore;

    MongoClient mongoClient;
    String databaseName;
    Morphia morphia = new Morphia();
    static final String SALT = "zebra";

    public UserService() {

        mongoClient = connection.getConnection();
        databaseName = connection.getDatabaseString();

        morphia.map(Area.class); //
        repository = new UserRepository(mongoClient,morphia, databaseName);
        datastore = morphia.createDatastore(mongoClient,databaseName); //
    }


    public void registerUser(Person person, String password){
        UsernameGenerator generator = new UsernameGenerator();

        String uName = generator.generate(person.getFirstName(), person.getLastName());

        String saltedPwd = SALT + password;
        String hashedPwd = generateHash(saltedPwd);

        User user = new User(uName, hashedPwd, person, "admin");

        repository.save(user);
    }

    public void changePassword(){

    }

    public String getUsername(Person person){
        //Retrieve person ID from DB
        //User that ID to search the user collection for corresponding ID
        return "";
    }

    public Boolean login(String username, String password){
        Boolean isAuthenticated = false;

        String saltedPwd = SALT + password;
        String hashedPwd = generateHash(saltedPwd);

        Query<User> findQry = datastore.createQuery(User.class).field("username").equalIgnoreCase(username);


        String storedPasswordHash =   findQry.toString();

        if (hashedPwd.equals(storedPasswordHash)){
            isAuthenticated = true;
        }else{
            isAuthenticated = false;
        }

        return isAuthenticated;
    }

    public String generateHash(String input){
        StringBuilder hash = new StringBuilder();

        try{
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

            for(int index = 0; index < hashedBytes.length; ++index){
                byte b = hashedBytes[index];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        return hash.toString();
    }
}

