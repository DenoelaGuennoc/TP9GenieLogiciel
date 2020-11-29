/**
 * @(#) Moniteur.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Moniteur extends Plongeur {

    public int numeroDiplome;
    public LinkedList<Embauche> emplois;

    public Moniteur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, GroupeSanguin groupeSanguin, int numeroDiplome) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance, groupeSanguin);
        this.numeroDiplome = numeroDiplome;
    }

    /**
     * Si ce moniteur n'a pas d'embauche, ou si sa dernière embauche est terminée,
     * ce moniteur n'a pas d'employeur.
     * @return l'employeur actuel de ce moniteur sous la forme d'un Optional
     */
    public Optional<Club> employeurActuel() {
         // TODO: Implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        for(int i = emplois.size()-1; i>0; i--){
            if(this.emplois.get(i).getDebut().isBefore(LocalDate.now())){
                if(!this.emplois.get(i).estTerminee()){
                    return Optional.of(this.emplois.get(i).getEmployeur());
                }
            }
        }
        return Optional.empty();
    }
    
    /**
     * Enregistrer une nouvelle embauche pour cet employeur
     * @param employeur le club employeur
     * @param debutNouvelle la date de début de l'embauche
     */
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) throws Exception {   
         // TODO: Implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");	
        LocalDate finDerniereEmbauche = this.emplois.getLast().getFin();
        Embauche nouvelleEmbauche = new Embauche(debutNouvelle, this, employeur);
        if(finDerniereEmbauche == null){
            throw new Exception("La dernière embauche de ce moniteur doit avoir une date de fin pour en créer une nouvelle");
        }
        else if(debutNouvelle.isAfter(finDerniereEmbauche)){
            this.emplois.addLast(nouvelleEmbauche);
        }
        else {
            throw new Exception("Une nouvelle embauche ne peut pas démarrer avant la fin de la précédente");
        }
    }

    public List<Embauche> emplois() {
         // TODO: Implémenter cette méthode
        //throw new UnsupportedOperationException("Pas encore implémenté");
        return this.emplois;
    }
    
    /**
     * Clôture l'embauche actuelle du moniteur
     */
    public void terminerEmbauche(LocalDate fin)throws Exception {
        for(int i = emplois.size()-1; i>0; i--){
            if(this.emplois.get(i).getDebut().isBefore(LocalDate.now())){
                this.emplois.get(i).terminer(fin);
            }
        }
    }

}
