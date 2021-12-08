package com.company;

public class BookingSystem {

    // According to the assumption that the maximum number of persons in this booking system is 1000 the array is set to that size
    // Da könnte man auch ganz lustig sein und die Liste aus Aufgabe A nehmen :D Wär eigentlich sogar besser weil da weniger speicher alloziert wird :D
    Person[] personDB = new Person[1000];
    Event[] eventDB = new Event[1000]; //Number of events is not defined, 1000 should be enough but needs to be reconsidered...
    Date date = null;

    public BookingSystem() {
        date = Date.getDate();
    }




    public void setDate(int date) {
        this.date.setCurrentDate(date);
        System.out.println("OK");
    }

    public void addPerson(Role role, String firstName, String lastName) {
        int latestIndex = countPersonsInDB();

        if (latestIndex >= 1000) {
            return;
        }

        personDB[latestIndex + 1] = new Person(role, firstName, lastName, latestIndex);
        System.out.println(latestIndex);
    }

    public void addCertificate(int personId, State3G state3G, int date) {
        Person person = getPersonWithID(personId);
        person.setCertificate(new Certificate(state3G, date));
    }

    public void printPerson(int personId) {
        Person person = getPersonWithID(personId);
        System.out.println(person.personId + ", " + person.firstName + ", " + person.lastName + ", " + person.certificate.getCertificateAsString());
    }

    public void printPeople(Role role) {
        int count = 0;
        for (Person person : personDB) {
            count++;
            if (person.role == role) {
                printPerson(person.personId);
            }
        }
        if (count == 0) {
            System.out.println("No" + role + "in the system");
        }
    }

    public void addEvent(int personId, String buildingName, int capacity, String regulation, int date) {
        int latestIndex = countEventsInDB();

        eventDB[latestIndex + 1] = new Event(personId, buildingName, capacity, regulation, date, latestIndex);
        System.out.println(latestIndex);
    }

    public void increaseSecurity(int eventId, int personId) {
        Person securityMember = this.getPersonWithID(personId);
        boolean isSecurity = securityMember.role == Role.security;
        Event event = this.getEventWithID(eventId);

        // Check if there are still available spots and if security member fulfills restrictions according to Corona regulation
        if (!isSecurity && !event.checkRestrictionSpots() && !event.checkRestriction3G(securityMember) && event.checkMemberAlreadyJoined(securityMember)) {
            System.out.println("Could not add security");
            return;
        }
        event.addMember(securityMember);
        System.out.println("OK");
    }

    public void bookSpot(int eventId, int personId) {
        Person student = this.getPersonWithID(personId);
        boolean isStudent = student.role == Role.student;
        Event event = this.getEventWithID(eventId);

        // Check if there are still available spots and if security member fulfills restrictions according to Corona regulation
        if (!isStudent && event.checkRestrictionSpots() && event.checkRestriction3G(student) && event.checkRestrictionSecurity()) {
            System.out.println("Could not book spot");
            return;
        }
        event.addMember(student);
        System.out.println("OK");
    }

    public void reportCase(int personId) {
        PersonEventCountTuple contactPersonsAndCount[] = new PersonEventCountTuple[][1000]; // Maximum value of persons is defined as 1000
        Event eventsOfLast14Days[] = new Event[1000]; // Da wär ne Liste echt besser...

        // Sorry für die scheiß Bezeichner :D
        int contactPersonsIndex = 0;
        int eventsOfLast14DaysIndex = 0;

        // Find all events of last 14 days
        for (Event event : eventDB) {
            if (event.date >= Date.getDate().getCurrentDate() - 14) { //Das sieht so komisch aus wegen dem Singlton Pattern
                                                                      // Bei dir ist das einfach der date Integer in bookingSystem
                // Check if person with personId joined event
                for (Person member : event.personDB) {
                    if (member.personId == personId) {
                        eventsOfLast14Days[eventsOfLast14DaysIndex++] = event;
                    }
                }
            }
        }

        contactPersonsAndCount = removeDuplicatesAndCount(getPersonsOfEvents(eventsOfLast14Days));

        for (PersonEventCountTuple tuple : contactPersonsAndCount) {
            String tupleString = tuple.getPerson().toString() + tuple.getCount();
            System.out.println(tupleString);
        }

    }

    private Person[] getPersonsOfEvents(Event[] events) {
        Person contactPersons[] = new Person[1000 * events.length]; // Maximum value of persons per event is defined as 1000
        int contactPersonsIndex = 0;

        // Get all persons of the given events
        for (Event event : events) { // Iterate over all given events
            for (Person person : event.personDB) { // Iterate over all persons who joined the event
                contactPersons[contactPersonsIndex++] = person;
            }
        }

        return contactPersons;
    }

    private PersonEventCountTuple[] removeDuplicatesAndCount(Person[] persons) {
        PersonEventCountTuple tuples[] = new PersonEventCountTuple[persons.length];
        int tupleIndex = 0;

        for (Person person : persons) {
            for (int i = 0; i < tuples.length; i++) {
                if (person.personId == tuples[i].getPersonID()) {
                    tuples[i].increasCount(); // If person is multiple times in array (because person joinde multiple events)
                                              // just increase count
                } else {
                    tuples[tupleIndex++] = new PersonEventCountTuple(person);
                }
            }
        }

        return tuples;
    }


    public Person getPersonWithID(int id) {
        Person tempPerson = null;
        for (Person person : personDB){
            if (person.personId == id);
        }
        return tempPerson;
    }

    public int countPersonsInDB() {
        int count = 0;
        while(personDB[count] != null) {
            count++;
        }
        return count;
    }

    public Event getEventWithID(int id) {
        Event tempEvent = null;
        for (Event event : eventDB){
            if (event.eventId == id);
        }
        return tempEvent;
    }

    public int countEventsInDB() {
        int count = 0;
        while(eventDB[count] != null) {
            count++;
        }
        return count;
    }

}
