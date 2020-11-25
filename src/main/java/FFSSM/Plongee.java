/**
 * @(#) Plongee.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Plongee {

	public Site lieu;
	public Moniteur chefDePalanquee;
	public LocalDate date;
	public int profondeur;
	public int duree;
        public LinkedList<Plongeur> participants;

	public Plongee(Site lieu, Moniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
		this.lieu = lieu;
		this.chefDePalanquee = chefDePalanquee;
		this.date = date;
		this.profondeur = profondeur;
		this.duree = duree;
	}

	public void ajouteParticipant(Plongeur participant) {
		// TODO: Implémenter cette méthode
		//throw new UnsupportedOperationException("Pas encore implémenté");
                this.participants.add(participant);
	}

	public LocalDate getDate() {
		return date;
	}

	/**
	 * Détermine si la plongée est conforme. 
	 * Une plongée est conforme si tous les plongeurs de la palanquée ont une
	 * licence valide à la date de la plongée
	 * @return vrai si la plongée est conforme
	 */
	public boolean estConforme() {
		// TODO: Implémenter cette méthode
		//throw new UnsupportedOperationException("Pas encore implémenté");
                for(int i=0; i < participants.size(); i++){
                    if(participants.get(i).getLicence() == null){
                        return false;
                    }
                    if(!participants.get(i).getLicence().estValide(date)){
                        return false;
                    }
                }
                return true;
        }

}
