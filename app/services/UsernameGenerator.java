package services;

import java.util.Random;

/**
 * Created by martian on 2017/03/20.
 */
public class UsernameGenerator {

    public UsernameGenerator() {
    }


    public String generate(String firstName, String lastName){

        Random numGenerator = new Random();

        int number = 0;

        number = numGenerator.nextInt(90);

        String concatenatedName;

        try{
            concatenatedName = firstName.charAt(0) + lastName.substring(0,4) + number;

        }catch(Exception e){

            concatenatedName = firstName.charAt(0) + lastName + number;
        }

    return concatenatedName;

    }
}
