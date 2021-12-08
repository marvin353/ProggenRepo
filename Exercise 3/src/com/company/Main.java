package com.company;
import java.lang;
import java.io;


public class Main {

    boolean quit = false;
    BookingSystem system = null;

    public static void main(String[] args) {

        Main main = new Main();
        main.system = new BookingSystem();

        while(!main.quit) {
            // Enter data using BufferReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // Reading data using readLine
            String input = reader.readLine();

            // Printing the read line
            System.out.println(input);
            parseInput(main, input);
        }

    }

    static void parseInput(Main main, String input) {
        String[] splittedInput = input.split("\\s+");
        String[] parameters = splittedInput[1].split(";");



        switch(splittedInput[0]){
            case "set-date":
                int dateValue = Integer.parseInt(parameters[0]);
                if (dateValue >= 0 && dateValue <= 364) {
                    main.system.setDate(dateValue);
                }
                break;
            case "add-person":
                Role role = Role.valueOf(parameters[0]);
                String firstName = parameters[1];
                String lastName = parameters[2];
                main.system.addPerson(role, firstName, lastName);
                break;
            case "add-certificate":
                int personId = Integer.parseInt(parameters[0]);
                State3G state3G = State3G.valueOf(parameters[1]);
                int date = Integer.parseInt(parameters[2]);
                main.system.addCertificate(personId, state3G, date);
                break;
            case "print-person":
                personId = Integer.parseInt(parameters[0]);
                main.system.printPerson(personId);
                break;
            case "print-people":
                role = Role.valueOf(parameters[0]);
                main.system.printPeople(role);
                break;
            case "add-event":
                personId = Integer.parseInt(parameters[0]);
                String buildingName = parameters[1];
                int capacity = Integer.parseInt(parameters[2]);
                String regulation = parameters[3];
                date = Integer.parseInt(parameters[4]);
                main.system.addEvent(personId, buildingName, capacity, regulation, date);
                break;
            case "increase-security":
                int eventId = Integer.parseInt(parameters[0]);
                personId = Integer.parseInt(parameters[1]);
                main.system.increaseSecurity(eventId, personId);
                break;
            case "book-spot":
                eventId = Integer.parseInt(parameters[0]);
                personId = Integer.parseInt(parameters[1]);
                main.system.bookSpot(eventId, personId);
                break;
            case "report-case":
                System.out.println("missing");
                break;
            default:
                System.out.println("Fehlerhafte Eingabe: Befehl unbekannt");
                break;
        }
    }



}
