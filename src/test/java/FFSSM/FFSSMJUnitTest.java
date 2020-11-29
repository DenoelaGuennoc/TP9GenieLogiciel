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
    Licence licenceA;
    
        
    @BeforeEach
    public void setUp() {
        rDuck = new Plongeur("1990129480096","Duck","Riri","15 rue des Canards, Donaldville","0615248395",LocalDate.of(1999,1,23),GroupeSanguin.BPLUS);
        fDuck = new Plongeur("1990129480097","Duck","Fifi","15 rue des Canards, Donaldville","0622548673",LocalDate.of(1999,1,23),GroupeSanguin.BPLUS);
        fDuck = new Plongeur("1990129480098","Duck","Loulou","15 rue des Canards, Donaldville","0645620314",LocalDate.of(1999,1,23),GroupeSanguin.BPLUS);
        dDuck = new Moniteur("1650829490102","Duck","Donald","15 rue des Canards, Donaldville","0614756234",LocalDate.of(1965,8,22),GroupeSanguin.AMOINS,50349812);
        bPicsou = new Moniteur("1551029480012","Picsou","Balthazar","1 rue du Coffre, Donaldville","0125489325",LocalDate.of(1955,10,11),GroupeSanguin.BMOINS,10452357);
        psd = new Club(bPicsou,"Picsou ScubaDucking","0123568478");
        licenceA = new Licence(rDuck,"231T54896K",LocalDate.of(2020,9,15),psd);
        
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
    
}
