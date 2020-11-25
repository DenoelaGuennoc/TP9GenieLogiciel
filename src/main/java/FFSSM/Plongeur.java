package FFSSM;

import java.time.LocalDate;

public class Plongeur extends Personne {
    
    public int niveau;
    public Licence licence;
    public Club club;
    
    public Plongeur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance){
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
    }
    
    public int getNiveau(){
        return niveau;
    }
    
    public void setNiveau(int niveau){
        this.niveau = niveau;
    }
    
    public void ajouteLicence(String numero, LocalDate delivrance){
        licence = new Licence(this, numero, delivrance,club);
    }
}
