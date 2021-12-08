package com.company;

enum Role {
    student,
    lecturer,
    security
}

public class Person {

    Role role = null;
    String firstName = "";
    String lastName = "";
    int personId = 0;
    Certificate certificate = null;

    public Person(Role role, String firstName , String lasteName, int personId) {

        this.firstName = firstName;
        this.lastName = lasteName;
        this.personId = personId;
        this.role = role;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public String toString() {
        String string = this.personId + ", " + this.firstName + ", " + this.lastName + ", " + this.certificate.getCertificateAsString();
        return string;
    }

}
