/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFSSM;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Denoëla
 */
public class FFSSMJUnitTest {
    
    Plongeur rDuck;
    Plongeur fDuck;
    Plongeur lDuck;
    Moniteur dDuck;
    Moniteur bPicsou;
    Club psd;
    Licence licenceRDuck;
    Site positionGalion;
    Plongee galionEspagnol;
    Plongee grotteEngloutie;
    
    
        
    @BeforeEach
    public void setUp() {
        rDuck = new Plongeur("1990129480096","Duck","Riri","15 rue des Canards, Donaldville","0615248395",LocalDate.of(1999,1,23),GroupeSanguin.BPLUS);
        fDuck = new Plongeur("1990129480097","Duck","Fifi","15 rue des Canards, Donaldville","0622548673",LocalDate.of(1999,1,23),GroupeSanguin.BPLUS);
        fDuck = new Plongeur("1990129480098","Duck","Loulou","15 rue des Canards, Donaldville","0645620314",LocalDate.of(1999,1,23),GroupeSanguin.BPLUS);
        dDuck = new Moniteur("1650829490102","Duck","Donald","15 rue des Canards, Donaldville","0614756234",LocalDate.of(1965,8,22),GroupeSanguin.AMOINS,50349812);
        bPicsou = new Moniteur("1551029480012","Picsou","Balthazar","1 rue du Coffre, Donaldville","0125489325",LocalDate.of(1955,10,11),GroupeSanguin.BMOINS,10452357);
        psd = new Club(bPicsou,"Picsou ScubaDucking","0123568478");
        licenceRDuck = new Licence(rDuck,"231T54896K",LocalDate.of(2020,9,15),psd);
        positionGalion = new Site("Amérique du sud","épave de galion");
        galionEspagnol = new Plongee(positionGalion, dDuck, LocalDate.of(2020,11,29), 37, 57);
        grotteEngloutie = new Plongee(dDuck);
        
    }
    
    /**
     * Test l'ajout d'une licence à un plongeur sans club
     * @throws Exception 
     */
    @Test
    public void testAjouteLicenceSansClub() throws Exception{
        //force le déclenchement d'une exception
        Exception thrown = assertThrows(Exception.class,
                () -> fDuck.ajouteLicence("125J15634D", LocalDate.of(2020,9,15)));
        
        //s'assure que le message d'exception affiché soit le bon
        assertEquals("Ce plongeur doit être rattaché à un club pour obtenir une licence",
                thrown.getMessage(),
                "Une licence ne doit pas pouvoir être ajoutée à un plongeur sans club");
        
        //s'assure que l'exception se soit bien déclenchée et qu'aucune licence n'ait été créée
        assertNull(fDuck.getLicence(),"Aucune licence ne devrait avoir été ajoutée à ce plongeur");
    }
    
    /**
     * Test l'ajout d'une licence à un plongeur
     * @throws Exception 
     */
    @Test
    public void testAjouteLicence() throws Exception{
        fDuck.setClub(psd);
        fDuck.ajouteLicence("125J15634D", LocalDate.of(2020,9,15));
        
        assertEquals(fDuck, fDuck.getLicence().getPossesseur(),
                "La licence n'a pas été ajoutée au bon plongeur");
        
        assertEquals(psd, fDuck.getLicence().getClub(),
                "La club délivrant la licence n'est pas le bon");
        
        assertEquals("125J15634D", fDuck.getLicence().getNumero(),
                "Le numéro de licence ajouté n'est pas le bon");
        
        assertEquals(LocalDate.of(2020,9,15),fDuck.getLicence().getDelivrance(),
                "La date de délivrance de la licence ajoutée n'est pas la bonne");
        
    }
    
    /**
     * Test l'organisation d'une plongee par un club
     */
    @Test
    public void testOrganisePlongee() throws Exception{
        psd.organisePlongee(galionEspagnol);
        
        assertTrue(psd.plongees.contains(galionEspagnol),
                "Ce club doit être organisateur de la plongée galionEspagnol");
        
        //force le déclenchement d'une exception
        Exception thrown = assertThrows(Exception.class,
                () -> psd.organisePlongee(galionEspagnol));
        
        //s'assure que le message d'exception affiché soit le bon
        assertEquals("Cette plongée a déjà été crée",
                thrown.getMessage(),
                "Une plongee ne peut être créée qu'une seule fois");
        
        psd.organisePlongee(grotteEngloutie);
        
        assertTrue(psd.plongees.contains(grotteEngloutie)); 
    }
    
    /**
     * Test d'ajout de articipants à une plongee
     */
    @Test
    public void testAjouteParticipant()throws Exception{
        galionEspagnol.ajouteParticipant(rDuck);
        
        //assertFalse(galionEspagnol.participants.isEmpty(), "PUTAIN DE %@*§#");
        
        //assertEquals(1,galionEspagnol.participants.size());
        
        assertTrue(galionEspagnol.participants.contains(rDuck),
                "Ce participant n'a pas été ajouté à la plongée");
        
        
        //Test de l'exception
        Exception thrown = assertThrows(Exception.class,
                () -> galionEspagnol.ajouteParticipant(rDuck));
        
        assertEquals("Ce plongeur est déjà inscrit pour cette plongée",
                thrown.getMessage(),
                "Un plongeur ne peut être inscrit qu'une seule fois à une même plongée");
        
        galionEspagnol.ajouteParticipant(lDuck);
        assertTrue(galionEspagnol.participants.contains(lDuck));
    }
    
    /**
     * Test de création d'une plongée non conforme
     */
    @Test
    public void testPlongeeNonConforme() throws Exception{
        psd.organisePlongee(grotteEngloutie);
        
        //si le chef de palanquee n'a pas de licence
        assertFalse(grotteEngloutie.estConforme(),
                "Cette plongée ne doit pas être conforme");
        
        //si le chef a une licence mais pas un des plongeurs
        dDuck.setClub(psd);
        dDuck.ajouteLicence("536A74368M", LocalDate.of(2020,8,12));
        grotteEngloutie.ajouteParticipant(fDuck);
                
        assertFalse(grotteEngloutie.estConforme(),
                "Cette plongée ne doit pas être conforme");
        
        assertTrue(psd.plongeesNonConformes().contains(grotteEngloutie),
                "Cette plongée devrait apparaître dans les plongées non conformes du club");
        
    }
}
