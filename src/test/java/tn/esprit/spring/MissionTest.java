package tn.esprit.spring;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tn.esprit.spring.services.EmployeServiceImpl;
import tn.esprit.spring.services.MissionServiceImpl;



@SpringBootTest
public class MissionTest {
	@Autowired
    MissionServiceImpl es;
    @Autowired
    MissionRepository missionRepository;
    @Autowired
    EmployeRepository emprepo;

    @Autowired
    EmployeServiceImpl employeService;
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    MissionServiceImpl missionService;

    private static final Logger l = LogManager.getLogger(MissionTest.class);


    @Test

    public void getAllMission() {

        l.info("Mission est : " + es.getAllMission());
    }
    
    

    @Test
    public void testCreateMission() {
    	int nbr_avantajout = es.getNombreMissionJPQL();
    	
    	long start = System.currentTimeMillis();
        int a = es.ajouterMission(new Mission("missionTest", "descriptionTest"));
        
        long elapsedTime = System.currentTimeMillis() - start;
        l.info("Method execution time: " + elapsedTime + " milliseconds.");
        
        Assertions.assertNotNull(a);
        int nbr_apresajout = es.getNombreMissionJPQL();
    	if(nbr_avantajout+1==nbr_apresajout)
    	{
    		l.info("Mission ajouter avec succe: " + a);
    		l.info("nbre"+ nbr_avantajout);
    	}
    	else {
    		l.info("Erreur dans l'ajout du mission: " + a);
    	}

    }


    @Test
    public void findAllMissionByEmployeJPQL() {
        var employe = new Employe("ala eddinne", "ghribi", "alaeddinne.ghribi@esprit.tn", true, Role.CHEF_DEPARTEMENT);
        employeService.ajouterEmploye(employe);
        es.findAllMissionByEmployeJPQL(employe.getId());
    }

    @Test
    public void getAllEmployeByMission() {
        int missionId = es.ajouterMission(new Mission("missionTesting", "descriptionTesting"));
        
        missionService.getAllEmployeByMission(missionId);

    }


    @Test
    public void Deletemission() {
    	int nbr_avantajout = es.getNombreMissionJPQL();
    	
    	long start = System.currentTimeMillis();
        int missionId = es.ajouterMission(new Mission("missionTesting", "descriptionTesting"));
        
        es.deleteMissionById(missionId);
        long elapsedTime = System.currentTimeMillis() - start;
        l.info("Method execution time: " + elapsedTime + " milliseconds.");
        
        int nbr_apresajout = es.getNombreMissionJPQL();
        if(nbr_avantajout-1==nbr_apresajout) {
        	
        	l.info("Mission est supprimé");
        }
        else {
        	l.info("Mission n'est pas supprimé");
        }
    
    
    }

    @Test
    public void affecterMissionADepartement() {
        int missionId = es.ajouterMission(new Mission("missionTest", "descriptionTest"));
        var departement = new Departement("Commerciale");
        departementRepository.save(departement);
        int depId = departement.getId();
        es.affecterMissionADepartement(missionId, depId);
        l.info("La mission qui a l'id: " + missionId + " est affecté au département avec l'id : " + depId);
    }
}
