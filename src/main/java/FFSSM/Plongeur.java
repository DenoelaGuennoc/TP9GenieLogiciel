package FFSSM;

import java.time.LocalDate;

public class Plongeur extends Personne {
    
    public int niveau;
    public Licence licence;
    public Club club;
    public GroupeSanguin groupeSanguin;
    
    public Plongeur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, GroupeSanguin groupeSanguin){
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
        this.groupeSanguin = groupeSanguin;
    }
    
    public int getNiveau(){
        return niveau;
    }
    
    public void setNiveau(int niveau){
        this.niveau = niveau;
    }
    
    public Licence getLicence(){
        return licence;
    }
    
    public Club getClub(){
        return club;
    }
    
    public void setClub(Club club){
        this.club = club;
    }
    
    public GroupeSanguin getGroupe(){
        return groupeSanguin;
    }
    
    public void ajouteLicence(String numero, LocalDate delivrance){
        licence = new Licence(this, numero, delivrance,club);
    }
}
