package com.company;

public class PersonEventCountTuple {

    private Person person = null;
    private int count = 1;

    public PersonEventCountTuple(Person person) {
        this.person = person;
        this.count = count;
    }

    public void increasCount() {
        this.count = this.count++;
    }

    public Person getPerson() {
        return person;
    }

    public int getCount() {
        return count;
    }

    public int getPersonID() {
        return person.personId;
    }

}
