package tn.esprit.spring.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.MissionRepository;
//import tn.esprit.spring.services.IEmployeService;
//import tn.esprit.spring.services.IEntrepriseService;
//import tn.esprit.spring.services.IMissionService;
//import tn.esprit.spring.services.ITimesheetService;

import java.util.List;

@Service
public class MissionServiceImpl implements IMissionService{

	@Autowired
    IEmployeService iemployeservice;
    @Autowired
    IEntrepriseService ientrepriseservice;
    @Autowired
    ITimesheetService itimesheetservice;

    @Autowired
    MissionRepository missionRepository;

    public void affecterMissionADepartement(int missionId, int depId) {
        itimesheetservice.affecterMissionADepartement(missionId, depId);

    }
    @Override
    public int ajouterMission(Mission mission) {
        missionRepository.save(mission);
        return mission.getId();
    }

    @Override
    public void deleteMissionById(int missionId) {
        missionRepository.deleteById(missionId);

    }

    public List<Mission> findAllMissionByEmployeJPQL(int employeId) {

        return itimesheetservice.findAllMissionByEmployeJPQL(employeId);
    }


    public List<Employe> getAllEmployeByMission(int missionId) {

        return itimesheetservice.getAllEmployeByMission(missionId);
    }
    
    @Override
    public List<Mission> getAllMission() {

        return (List<Mission>) missionRepository.findAll();
    }
    
	@Override
	public int getNombreMissionJPQL() {
		return missionRepository.countemp();
	}

}
