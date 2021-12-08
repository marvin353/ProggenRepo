package com.company;

enum State3G {
    no_certificate,
    vaccinated,
    recovered,
    tested
}

public class Certificate{

    State3G state3G = null;
    int expirationDate = 0;

    public Certificate(State3G state_3G, int expiratoionDate){
        this.state3G = state_3G;
        this.expirationDate = expiratoionDate;
    }

    public void updateCertificateState(State3G state3G) {
        this.state3G = state3G;
    }

    public void updateCertificateExpirationDate(int expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCertificateAsString() {
        if (this.state3G == State3G.no_certificate){
            return "no certificate";
        }
        return this.state3G.toString();
    }

}