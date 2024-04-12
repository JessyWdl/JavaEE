package com.jessy.entity.entites;

import com.jessy.entity.exception.MonException;

public class Client extends Societe {
    private Double ChiffreAffaire;
    private int NbEmployes;

    public Client(String RaisonSociale, String NumRue, String NomRue, String CodePostal,
                  String Ville, String Tel, String Email, Double ChiffreAffaire, int NbEmployes, String Commentaire) throws MonException {
        super(RaisonSociale, NumRue, NomRue, CodePostal, Ville, Tel, Email, Commentaire);
        setChiffreAffaire(ChiffreAffaire);
        setNbEmployes(NbEmployes);
    }

    public Client() {
    }

    public Double getChiffreAffaire() {
        return ChiffreAffaire;
    }

    public int getNbEmployes() {
        return NbEmployes;
    }

    public void setChiffreAffaire(double ChiffreAffaire) throws MonException {
        if (ChiffreAffaire <= 200) {
            throw new MonException("Chiffre d'affaire trop peu élevé");
        }
        this.ChiffreAffaire = ChiffreAffaire;
    }

    public void setNbEmployes(int NbEmployes) throws MonException {
        if (NbEmployes < 0) {
            throw new MonException("Nombre d'employés ne peut pas être négatif");
        }
        this.NbEmployes = NbEmployes;
    }

    public String toString() {
        return "Client{" + super.toString() +
                ", ChiffreAffaire=" + ChiffreAffaire + '\'' +
                ", NbEmployes=" + NbEmployes + '\'' +
                "}";
    }
}
