package com.udacity.capstone.trackmyhealth.utils;

import java.util.ArrayList;
import java.util.Locale;


public class PopulateSpinner {

    public static ArrayList<String> GetCountry() {

        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        return countries;
    }

    public static ArrayList<String> GetStates() {

        int stateCount = 0;
        ArrayList<String> stateNames = new ArrayList<String>();
        stateNames.add("None");
        stateNames.add("Alabama");
        stateNames.add("Alaska");
        stateNames.add( "Arizona");
        stateNames.add( "Arkansas");
        stateNames.add( "California");
        stateNames.add( "Colorado");
        stateNames.add( "Connecticut");
        stateNames.add( "Delaware");
        stateNames.add( "Florida");
        stateNames.add( "Georgia");
        stateNames.add( "Hawaii");
        stateNames.add( "Idaho");
        stateNames.add( "Iowa");
        stateNames.add( "Illinois");
        stateNames.add( "Indiana");
        stateNames.add( "Kansas");
        stateNames.add( "Kentucky");
        stateNames.add( "Louisiana");
        stateNames.add( "Maine");
        stateNames.add( "Maryland");
        stateNames.add( "Massachusetts");
        stateNames.add( "Michigan");
        stateNames.add( "Minnesota");
        stateNames.add( "Mississippi");
        stateNames.add( "Missouri");
        stateNames.add( "Montana");
        stateNames.add( "Nebraska");
        stateNames.add( "Nevada");
        stateNames.add( "New Hampshire");
        stateNames.add( "New Jersey");
        stateNames.add( "New Mexico");
        stateNames.add( "New York");
        stateNames.add( "North Carolina");
        stateNames.add( "North Dakota");
        stateNames.add( "Ohio");
        stateNames.add( "Oklahoma");
        stateNames.add( "Oregon");
        stateNames.add( "Pennsylvania");
        stateNames.add( "Rhode Island");
        stateNames.add( "South Carolina");
        stateNames.add( "South Dakota");
        stateNames.add( "Tennessee");
        stateNames.add( "Texas");
        stateNames.add( "Utah");
        stateNames.add( "Vermont");
        stateNames.add( "Virginia");
        stateNames.add( "Washington");
        stateNames.add( "Washington DC");
        stateNames.add( "West Virgina");
        stateNames.add( "Wisconsin");
        stateNames.add( "Wyoming");

        return stateNames;
    }
}
