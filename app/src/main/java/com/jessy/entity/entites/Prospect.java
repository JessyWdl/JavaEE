package com.jessy.entity.entites;

import com.jessy.entity.exception.MonException;

import java.time.LocalDate;

public class Prospect extends Societe{

    private LocalDate DateProspect;
    private String ProspectInteresse;

    public Prospect(String RaisonSociale, String NumRue, String NomRue, String CodePostal, String Ville,
                    String Tel, String Email, LocalDate DateProspect, String ProspectInteresse, String Commentaire) throws MonException {
        super(RaisonSociale, NumRue, NomRue, CodePostal, Ville, Tel, Email, Commentaire);
        setDateProspect(DateProspect);
        setProspectInteresse(ProspectInteresse);
    }

    public Prospect(){
    }

    public LocalDate getDateProspect() {
        return DateProspect;
    }

    public String getProspectInteresse(){
        return ProspectInteresse;
    }

    public void setDateProspect(LocalDate DateProspect) throws MonException {
        if (DateProspect == null){
            throw new MonException("Date invalide");
        }
        this.DateProspect = DateProspect;
    }
    public void setProspectInteresse(String ProspectInteresse) throws MonException {
        if (ProspectInteresse == null || ProspectInteresse.isEmpty()){
        throw new MonException("Prospect intéressé ne peut être vide");
        }
        this.ProspectInteresse = ProspectInteresse;
    }
    public String toString(){
        return "prospect{" + super.toString() +
                ", DateProspect=" + DateProspect + '\'' +
                ", ProspectInteresse=" + ProspectInteresse + '\'' +
                '}';
    }
}
