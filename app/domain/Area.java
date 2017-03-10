package domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

/**
 * Created by martian on 2017/02/25.
 */
@Entity(noClassnameStored = true, value = "area")
public class Area {
    @Id
    private ObjectId id;

    @Indexed(unique = false)
    private String name;

    private String areaID;
    private String areaCode;

    public Area() {
    }

    public Area(String name, String areaID, String areaCode) {
        this.name = name;
        this.areaID = areaID;
        this.areaCode = areaCode;
    }

    public String getAreaID() {
        return areaID;
    }

    public void setAreaID(String areaID) {
        this.areaID = areaID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
