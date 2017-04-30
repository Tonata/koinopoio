package services;

import com.mongodb.MongoClient;
import conf.Connection;
import domain.Area;
import domain.Person;
import domain.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import repository.AreaRepository;
import repository.UserRepository;

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

    public UserService() {

        mongoClient = connection.getConnection();
        databaseName = connection.getDatabaseString();

        morphia.map(Area.class);
        repository = new UserRepository(mongoClient,morphia, databaseName);
        datastore = morphia.createDatastore(mongoClient,databaseName);
    }

//    public String createUsername(Person person){
//        UsernameGenerator generator = new UsernameGenerator();
//
//        String uName = generator.generate(person.getFirstName(), person.getLastName());
//
//        return uName;
//    }

    public void addUser(Person person){
        UsernameGenerator generator = new UsernameGenerator();

        String uName = generator.generate(person.getFirstName(), person.getLastName());

        User user = new User(uName, "0000", person, "admin"); // Fix password

        repository.save(user);
    }

    public void changePassword(){

    }

    public void hashPassword(){

    }
}

