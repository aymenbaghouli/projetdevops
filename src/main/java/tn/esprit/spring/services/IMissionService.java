package tn.esprit.spring.services;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;

import java.util.List;
public interface IMissionService {
	public int ajouterMission(Mission mission);
	public void deleteMissionById(int entrepriseId);
	public List<Mission> findAllMissionByEmployeJPQL(int employeId);
	public List<Employe> getAllEmployeByMission(int missionId);
	public List<Mission> getAllMission();
	public int getNombreMissionJPQL();

}
