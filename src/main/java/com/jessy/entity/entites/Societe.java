package com.jessy.entity.entites;

import com.jessy.entity.exception.MonException;
import static com.jessy.entity.utlitaire.Regex.*;

public class Societe {

    private int ID;
    private String RaisonSociale;
    private String NumRue;
    private String NomRue;
    private String CodePostal;
    private String Ville;
    private String Tel;
    private String Email;
    private String Commentaire;

    public Societe(String RaisonSociale, String NumRue, String NomRue, String CodePostal, String Ville, String Tel, String Email, String Commentaire) throws MonException {
        setRaisonSociale(RaisonSociale);
        setNumRue(NumRue);
        setNomRue(NomRue);
        setCodePostal(CodePostal);
        setVille(Ville);
        setTel(Tel);
        setEmail(Email);
        setCommentaire(Commentaire);
    }
    public Societe(){
    }
    public int getID() {
        return ID;
    }
    public String getRaisonSociale() {
        return RaisonSociale;
    }
    public String getNumRue() {
        return NumRue;
    }
    public String getNomRue() {
        return NomRue;
    }
    public String getCodePostal() {
        return CodePostal;
    }
    public String getVille() {
        return Ville;
    }
    public String getTel() {
        return Tel;
    }
    public String getEmail() {
        return Email;
    }
    public String getCommentaire(){return Commentaire;}
    public void setID(int ID) throws MonException {
        if (ID < 0){
            throw new MonException("ID ne peut être négatif");
        }
        this.ID = ID;
    }
    public void setRaisonSociale(String RaisonSociale) throws MonException {
        if (RaisonSociale == null || RaisonSociale.isEmpty()){
            throw new MonException("La raison sociale ne peut pas être vide");
        }
        this.RaisonSociale = RaisonSociale;
    }
    public void setNumRue(String NumRue) throws MonException {
        if (NumRue==null || NumRue.isEmpty()){
            throw new MonException("Le numéro de Rue ne peut pas être vide");
        }
        this.NumRue = NumRue;
    }
    public void setNomRue(String NomRue) throws MonException{
        if (NomRue==null || NomRue.isEmpty()){
            throw new MonException("Le nom de rue ne peut être vide");
        }
        this.NomRue = NomRue;
    }
    public void setCodePostal(String CodePostal) throws MonException{
        if (CodePostal == null || CodePostal.isEmpty()){
            throw new MonException("Le code postal ne peut être vide");
        }
        this.CodePostal = CodePostal;
    }
    public void setVille(String Ville) throws MonException{
        if (Ville == null || Ville.isEmpty()){
            throw new MonException("La Ville ne peut être vide");
        }
        this.Ville = Ville;
    }
    public void setTel(String Tel)throws MonException{
        if (Tel == null || Tel.isEmpty()){
            throw new MonException("Le numéro de téléphone ne peut être vide");
        } else if (Tel.length() < 10 || Tel.length() > 15) {
            throw new MonException("Le numéro de téléphone est trop court ou trop long");
        }
        this.Tel = Tel;
    }
    public void setEmail(String Email) throws MonException {
        if (!validate(Email)){
            throw new MonException("L'email n'est pas conforme");
        }
        this.Email = Email;
    }
    public void setCommentaire(String Commentaire){
        if(Commentaire.isEmpty()){
            Commentaire = "-";
        }
        this.Commentaire = Commentaire;
    }
    @Override
    public String toString() {
        return "Societe{" +
                "Id=" + ID +
                ", RaisonSociale='" + RaisonSociale + '\'' +
                ", NumRue='" + NumRue + '\'' +
                ", NomRue='" + NomRue + '\'' +
                ", CodePostal='" + CodePostal + '\'' +
                ", Ville='" + Ville + '\'' +
                ", Tel='" + Tel + '\'' +
                ", Email='" + Email + '\'' +
                ", Commentaire=" + Commentaire +
                '}';
    }
}
