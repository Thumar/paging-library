package com.androidkt.archpaging.db;

import com.androidkt.archpaging.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by brijesh on 18/9/17.
 */

public class DatabaseCreator {

    private String[] firstName = new String[]{"Noah", "Emma", "Liam", "Olivia", "William", "Ava", "Mason", "Sophia", "James",
            "Isabella", "Benjamin", "Mia", "Jacob", "Charlotte", "Michael", "Abigail", "Elijah", "Emily", "Ethan", "Harper"};


    private String[] street = new String[]{
            "High Street", "Station Road", "Main Street", "Park Road", "Church Road", "Church Street", "London Road", "Victoria Road", "Green Lane",
            "The Avenue", "The Crescent", "Queens Road", "New Road", "Grange Road", "Kings Road", "Kingsway", "Windsor Road", "Highfield Road", "Mill Lane",
            "Alexander Road", "York Road", "St. John’s Road", "Manor Road", "Church Lane", "Park Avenue"};

    private String[] city = new String[]{
            "Ashland", "Aspen", "Atascadero", "Athens", "Atlanta", "Auburn", "Austin", "Ayer", "Babylon", "Bainbridge"
    };

    private String[] state = new String[]{
            "New Hampshire", "New Jersey", "New Mexico", "New York"};

    public List<User> getRandomUserList() {
        List<User> users = new ArrayList<>();
        User tempUser;
        int cityRange = city.length;
        int stateRange = state.length;
        int nameRange = firstName.length;
        int streetRange = street.length;
        String address;
        Random random = new Random();
        for (int i = 1; i <= 200; i++) {
            address = state[random.nextInt(stateRange)] + "," + city[random.nextInt(cityRange)] + "," +
                    String.valueOf(random.nextInt(99999)) + "," + street[random.nextInt(streetRange)];
            tempUser = new User();
            tempUser.address = address;
            tempUser.firstName = firstName[random.nextInt(nameRange)];
            users.add(tempUser);
        }
        return users;
    }

}
