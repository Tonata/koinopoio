package services;

import domain.Person;
import domain.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import repository.UserRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.util.Arrays.asList;

/**
 * Created by martian on 2017/05/01.
 */
public class UserService {

    private UserRepository repository;
    private Datastore datastore;
    private MapClasses mapping;
    private static final String SALT = "zebra";

    public UserService() {

        mapping     = new MapClasses();
        repository  = new UserRepository(mapping.getClient(),mapping.mapArea(), mapping.getDbName());
        datastore   = mapping.getDatastore();
    }

    /* Register user with system generated username */
    public void registerUser(Person person, String password, String role){
        UsernameGenerator generator = new UsernameGenerator();

        String uName = generator.generate(person.getFirstName(), person.getLastName());

        String saltedPwd = SALT + password;
        String hashedPwd = generateHash(saltedPwd);

        Query<Person> personQry = datastore.createQuery(Person.class).field("firstName").equal(person.getFirstName()).field("lastName").equal(person.getLastName());

        Person retrievedPerson = (Person) personQry.asList().get(0);

        User user = new User(uName, hashedPwd, retrievedPerson, role);

        repository.save(user);
    }

    /* Register with user selected username */
    public void registerUser(Person person, String username, String password, String role){

        String saltedPwd = SALT + password;
        String hashedPwd = generateHash(saltedPwd);

        Query<Person> personQry = datastore.createQuery(Person.class).field("firstName").equal(person.getFirstName()).field("lastName").equal(person.getLastName());

        Person retrievedPerson = (Person) personQry.asList().get(0);

        User user = new User(username, hashedPwd, retrievedPerson, role);

        repository.save(user);
    }

    public void changePassword(String username, String newPassword){

        String saltedPwd = SALT + newPassword;
        String hashedPwd = generateHash(saltedPwd);

        Query<User> findQry = datastore.createQuery(User.class).field("username").equal(username);

        UpdateOperations<User> updateQry = datastore.createUpdateOperations(User.class).set("password",hashedPwd);
        datastore.update(findQry,updateQry);

    }

    public String getUsername(Person person){

        Query<Person> personQry = datastore.createQuery(Person.class).field("firstName").equal(person.getFirstName()).field("lastName").equal(person.getLastName());

        Person retrievedPerson = (Person) personQry.asList().get(0);

        Query<User> findQry = datastore.createQuery(User.class).field("person").equal(retrievedPerson);

        return findQry.asList().get(0).getUsername();

    }

    public Boolean login(String username, String password){
        Boolean isAuthenticated = false;

        String saltedPwd = SALT + password;
        String hashedPwd = generateHash(saltedPwd);

        Query<User> findQry = datastore.createQuery(User.class).field("username").equal(username);

        String storedPasswordHash =  findQry.asList().get(0).getPassword();

        if (hashedPwd.equals(storedPasswordHash)){
            isAuthenticated = true;

        }else {
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

