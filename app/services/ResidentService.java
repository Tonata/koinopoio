package services;

import domain.Area;
import domain.Resident;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import repository.AreaRepository;
import repository.ResidentRepository;

import java.util.List;

/**
 * Created by martian on 2017/04/17.
 */
public class ResidentService {

    private ResidentRepository repository;
    private Datastore datastore;
    private MapClasses mapping;

    public ResidentService() {

        mapping     = new MapClasses();
        repository  = new ResidentRepository(mapping.getClient(),mapping.mapArea(), mapping.getDbName());
        datastore   = mapping.getDatastore();
    }

    public void addResident(Resident resident){
        //Verify areaName exists
    }

    /* Returns isSaved: true  - if record is saved to database
    *                   false - if error occurs in verifying areaName */
    public Boolean addResident(String contactNumber, String name, String emailAddress, String areaName){

        //Verify that contact number is in correct format i.e 26481/85 xxx xxxx
        //Verify areaName exists
        Boolean isSaved = false;
        Query<Area> findQry = datastore.createQuery(Area.class).field("name").containsIgnoreCase(areaName);
        if (!findQry.asList().isEmpty()){

            Resident resident = new Resident(contactNumber, name, emailAddress, areaName);
            repository.save(resident);
            isSaved = true;
        }
        else{
            isSaved = false;
        }

        return isSaved;
    }

    public void addAllResidents(List<Resident> resident){
        //Verify areaName exists
    }

    public void updateResidentContactNumber(String name, String contactNumber){

        try{
            Query<Resident> findQry = datastore.createQuery(Resident.class).field("name").equalIgnoreCase(name);
            UpdateOperations<Resident> updateQry = datastore.createUpdateOperations(Resident.class).set("contactNumber",contactNumber);
            datastore.update(findQry,updateQry);

        }catch (Exception ex){
            System.err.println("Update contact number expection: " + ex.getMessage());
        }

    }

    public List<Resident> getAllResidentByArea(String areaName){
        Query<Resident> residentQry = datastore.createQuery(Resident.class).field("areaName").equalIgnoreCase(areaName);
        return residentQry.asList();
    }


    public List<Resident> getAllResidents(){
        Query<Resident> residentQry = datastore.createQuery(Resident.class);
        return residentQry.asList();
    }

    public List<Resident> getAllResidentByChar(String character){
        Query<Resident> residentQry = datastore.createQuery(Resident.class).field("name").equalIgnoreCase(character);
        return residentQry.asList();
    }

    /* Returns isMoved: true  - if resident is successfully update with a different area name
   *                   false  - if error occurs in updating area name */
    public Boolean moveResident(String residentName, String areaTo){

        Boolean isMoved = false;
        try{
            Query<Resident> findQry = datastore.createQuery(Resident.class).field("name").equalIgnoreCase(residentName);
            UpdateOperations<Resident> updateQry = datastore.createUpdateOperations(Resident.class).set("areaName",areaTo);
            datastore.update(findQry,updateQry);
            isMoved = true;

        }catch (Exception ex){
            System.err.println("Move resident operation expection: " + ex.getMessage());
        }

        return isMoved;
    }

}
