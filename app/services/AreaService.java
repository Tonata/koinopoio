package services;

import com.mongodb.MongoClient;
import conf.Connection;
import domain.Area;
import domain.Resident;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import repository.AreaRepository;

import java.util.List;

/**
 * Created by martian on 2017/04/17.
 */
public class AreaService {

    private AreaRepository repository;
    private Datastore datastore;
    private MapClasses mapping;

    public AreaService() {

        mapping     = new MapClasses();
        repository  = new AreaRepository(mapping.getClient(),mapping.mapArea(), mapping.getDbName());
        datastore   = mapping.getDatastore();
    }

    public Object addArea(Area area){
       return repository.save(area).getId();
    }

    public void addAllAreas(List<Area> areas){

    }

    public void addArea(String name, String areaID, String areaCode){
        Area area = new Area(name,areaID, areaCode);
        repository.save(area);
    }

    // Needs Bug checking
    public void updateAreaName(String oldName, String newName){

        try{
            Query<Area> findQry = datastore.createQuery(Area.class).field("name").equalIgnoreCase(oldName);
            UpdateOperations<Area> updateQry = datastore.createUpdateOperations(Area.class).set("name",newName);
            datastore.update(findQry,updateQry);
        }catch (Exception ex){
            System.err.println("Update area name expection: " + ex.getMessage());
        }

    }

    // Needs Bug checking
    public void updateAreaCode(String oldCode, String newCode){

        try{
            Query<Area> findQry = datastore.createQuery(Area.class).field("areaCode").equalIgnoreCase(oldCode);
            UpdateOperations<Area> updateQry = datastore.createUpdateOperations(Area.class).set("areaCode",newCode);
            datastore.update(findQry,updateQry);

        }catch (Exception ex){
            System.err.println("Update area code expection: " + ex.getMessage());
        }


    }

    public List<Area> getAllAreas(){

        Query<Area> areaQry = datastore.createQuery(Area.class);
        return areaQry.asList();
    }

    public List<Area> getAreaByCode(String areaCode){

        Query<Area> areaQry = datastore.createQuery(Area.class).field("areaCode").equalIgnoreCase(areaCode);
        return areaQry.asList();
    }

    public List<Area> getAreaByName(String character){

        Query<Area> areaQry = datastore.createQuery(Area.class).field("areaCode").contains(character);
        return areaQry.asList();
    }

    public long getTotalResidents(String areaName){

        Query<Resident> areaQry = datastore.createQuery(Resident.class).field("name").equalIgnoreCase(areaName);
        return areaQry.count();

    }

//    public Boolean deleteAllAreas(){
//        return false;
//    }
//
//    public Boolean deleteAreaByCode(String areaCode){
//    }
//
//    public Boolean deleteAreaByName(String character){
//        return false;
//    }
}
