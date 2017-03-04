package domain;

/**
 * Created by martian on 2017/02/25.
 */
public class Person {

    private String firstName;
    private String lastName;
    private String gender;
    private String id;

    private Person (Builder b){
        firstName       = b.firstName;
        lastName        = b.lastName;
        gender          = b.gender;
        id              = b.id;
    }

    public static class Builder{
        private String firstName;
        private String lastName;
        private String gender;
        private String id;

        public Builder(){

        }

        public Builder firstName(String fName){
            firstName = fName;
            return this;
        }

        public Builder lastName (String lName){
            lastName = lName;
            return this;
        }

        public Builder gender (String g){
            gender = g;
            return this;
        }

        public Builder id(String i){
            id = i;
            return this;
        }

        public Builder person (Person p){
            firstName       = p.getFirstName();
            lastName        = p.getLastName();
            gender          = p.getGender();
            id              = p.getId();
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
