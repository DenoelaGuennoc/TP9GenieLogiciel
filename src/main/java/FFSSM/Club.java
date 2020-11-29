/**
 * @(#) Club.java
 */
package FFSSM;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Club {

 
    public Moniteur president;
    public String nom;
    public String adresse;
    public String telephone;
    public LinkedList<Plongee> plongees = new LinkedList<>();

    public Club(Moniteur président, String nom, String telephone) {
        this.president = président;
        this.nom = nom;
        this.telephone = telephone;
    }

    /**
     * Calcule l'ensemble des plongées non conformes organisées par ce club.
     * Une plongée est conforme si tous les plongeurs de la palanquée ont une licence
     * valide à la date de la plongée
     * @return l'ensemble des plongées non conformes
     */
    public Set<Plongee> plongeesNonConformes() {
         // TODO: Implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        Set plongeesNC = new HashSet();
        for(int i=0; i < plongees.size(); i++){
            if(!plongees.get(i).estConforme()){
                plongeesNC.add(plongees.get(i));
            }
        }
        return plongeesNC;
        
    }

    /**
     * Enregistre une nouvelle plongée organisée par ce club
     * @param p la nouvelle plongée
     */
    public void organisePlongee(Plongee p) throws Exception {
         // TODO: Implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        for(int i=0; i < plongees.size(); i++){
            if(plongees.get(i).equals(p)){
                throw new Exception("Cette plongee a déjà été crée");
            }
        }
        this.plongees.add(p);
    }
    
    
    public Moniteur getPresident() {
        return president;
    }

    public void setPresident(Moniteur president) {
        this.president = president;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Club{" + "président=" + president + ", nom=" + nom + ", adresse=" + adresse + ", telephone=" + telephone + '}';
    }

}
