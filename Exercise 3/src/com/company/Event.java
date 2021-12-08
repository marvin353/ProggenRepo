package com.company;

import java.util.Arrays;

public class Event {
    int personId = 0;
    String buildingName = "";
    int capacity = 0;
    String regulation = "";
    int date = 0;
    int eventId = 0;

    Person[] eventMembers = null;
    int eventMembersCount = 0;

    public Event(int personId, String buildingName, int capacity, String regulation, int date, int eventId) {
        this.personId = personId;
        this.buildingName = buildingName;
        this.capacity = capacity;
        this.regulation = regulation;
        this.date = date;
        this.eventId = eventId;

        eventMembers = new Person[capacity];
    }

    public void addMember(Person person) {
        eventMembers[eventMembersCount] = person;
        eventMembersCount++;
    }

    public boolean checkRestriction3G(Person person) {
        State3G state3G = person.certificate.state3G;
        if(this.regulation == "2G" && (state3G == State3G.vaccinated || state3G == State3G.recovered)) {
            return true;
        } else if (this.regulation == "3G" && (state3G == State3G.vaccinated || state3G == State3G.recovered || state3G == State3G.tested)) {
            return true;
        } else {
            return false;
        }


    }

    Person[] personDB = new Person[1000];


    public boolean checkRestrictionSpots() {
        if (eventMembersCount >= this.capacity) {
            return false;
        }
        return true;

        

    }

    // Maybe bad solution... takes too long so it could be better to use explicit counter variables...
    public boolean checkRestrictionSecurity() {

        int numberOfSecurity = 0;
        int numberOfStudents = 0;

        for (Person person : eventMembers) {
            if (person.role == Role.security) {
                numberOfSecurity++;
            }
            if (person.role == Role.student) {
                numberOfStudents++;
            }
        }

        // Calculate ratio - should be 1 security member to 5 students
        if ((numberOfStudents + 1) / 5 <= numberOfSecurity) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkMemberAlreadyJoined(int personId) {
        for (Person person : eventMembers) {
            if (person.personId == personId) {
                return true;
            }
        }
        return false;
    }



}