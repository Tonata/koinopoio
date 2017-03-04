package domain;

/**
 * Created by martian on 2017/02/25.
 */
public class Area {

    private String areaID;
    private String name;
    private String areaCode;

    private Area(Builder b){
        areaID      = b.areaID;
        name        = b.name;
        areaCode    = b.areaCode;
    }

    public static class Builder{
        private String areaID;
        private String name;
        private String areaCode;

        public Builder(){

        }

        public Builder areaID(String aID){
            areaID = aID;
            return this;
        }

        public Builder name(String n){
            name = n;
            return this;
        }

        public Builder areaCode(String aCode){
            areaID = aCode;
            return this;
        }

        public Builder area(Area a){
            areaID      = a.getAreaID();
            name        = a.getName();
            areaCode    = a.getAreaCode();
            return this;
        }

        public Area build(){
            return new Area(this);
        }
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
}
