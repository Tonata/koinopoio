package services;

import com.mongodb.MongoClient;
import conf.Connection;
import domain.Area;
import domain.Person;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import repository.AreaRepository;
import repository.PersonRepository;

import java.util.List;

/**
 * Created by martian on 2017/05/01.
 */
public class PersonService {

    PersonRepository repository;
    Connection connection = new Connection();
    Datastore datastore;

    MongoClient mongoClient;
    String databaseName;
    Morphia morphia = new Morphia();

    public PersonService() {

        mongoClient = connection.getConnection();
        databaseName = connection.getDatabaseString();

        morphia.map(Area.class);
        repository = new PersonRepository(mongoClient,morphia, databaseName);
        datastore = morphia.createDatastore(mongoClient,databaseName);
    }

    public Object addPerson(Person person){
        return repository.save(person).getId();
    }

    public void addAllPersons(List<Person> people){

    }

    public void updateFirstName(String oldName, String newName){
        try{
            Query<Person> findQry = datastore.createQuery(Person.class).field("firstName").equalIgnoreCase(oldName);
            UpdateOperations<Person> updateQry = datastore.createUpdateOperations(Person.class).set("firstName",newName);
            datastore.update(findQry,updateQry);
        }catch (Exception ex){
            System.err.println("Update area name expection: " + ex.getMessage());
        }
    }

    public void updateLastName(String oldName, String newName){
        try{
            Query<Person> findQry = datastore.createQuery(Person.class).field("lastName").equalIgnoreCase(oldName);
            UpdateOperations<Person> updateQry = datastore.createUpdateOperations(Person.class).set("lastName",newName);
            datastore.update(findQry,updateQry);
        }catch (Exception ex){
            System.err.println("Update area name expection: " + ex.getMessage());
        }
    }

    public void updateGender(String oldGender, String newGender){
        try{
            Query<Person> findQry = datastore.createQuery(Person.class).field("gender").equalIgnoreCase(oldGender);
            UpdateOperations<Person> updateQry = datastore.createUpdateOperations(Person.class).set("gender",newGender);
            datastore.update(findQry,updateQry);
        }catch (Exception ex){
            System.err.println("Update area name expection: " + ex.getMessage());
        }
    }

    public List<Person> getAllPersons(){
        Query<Person> personQry = datastore.createQuery(Person.class);
        return personQry.asList();
    }

    public List<Person> getAllPersonsByChar(String character){
        Query<Person> personQry = datastore.createQuery(Person.class).field("firstName").contains(character);
        return personQry.asList();
    }

    public List<Person> getAllPersonsByLastName (String character){
        Query<Person> personQry = datastore.createQuery(Person.class).field("lastName").contains(character);
        return personQry.asList();
    }

    public List<Person> getAllPersonsByGender (String character){
        Query<Person> personQry = datastore.createQuery(Person.class).field("gender").equal(character);
        return personQry.asList();
    }
}
