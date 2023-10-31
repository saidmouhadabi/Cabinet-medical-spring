package example.dedo.api;

import java.util.List;
// import java.util.List;
import java.util.Optional;
// import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RestController;

import example.dedo.dao.PatientDao;
import example.dedo.model.Patient;


@Controller
// @RequestMapping("api/patient")
public class PatientController {
    
    @Autowired
    private PatientDao patientDAO;

    
    @GetMapping("/API")
    @ResponseBody
    public List<Patient> homePageAPI(Model model){
        //model.addAttribute("patientList",patientDAO.findAll());
		return patientDAO.findAll();
    }

    @GetMapping("/patient")
	public String patientPage(Model model, @RequestParam(name = "cin",defaultValue = "")String cin) {
        List <Patient>patientList=patientDAO.findByCinContaining(cin);
		model.addAttribute("patientList",patientList);
        model.addAttribute("cin",cin);
       // model.addAttribute("cin",cin);
		return "pages/patients_table";
	}

    /*@GetMapping("/")
    public String homePage(Model model) {
        return "test";
    }*/
    @GetMapping("/test2")
    public List<Patient> test(Model model){
        return patientDAO.Patients();
    }

    @GetMapping("/savePatientPage")
 	public String savePatientPage(Model model) {
 		Patient patient= new Patient();
        model.addAttribute("patient",patient);
 	    return "/pages/patient_form";
 	}

    @PostMapping("/savePatientAPI")
    public Patient savePatientAPI(@RequestBody Patient patient) {
       return patientDAO.save(patient);
    }

    @PostMapping("/savePatient")
    public String savePatient(@ModelAttribute("patient") Patient patient) {
       patientDAO.save(patient);
       return "redirect:/patient";
    }

    @PutMapping("/updatePatientAPI")
    public Patient showUpdateRDVPageAPI( @RequestBody Patient patient) {
        //rdv.setId_rdv(id);
    	return patientDAO.save(patient);

    }

    @GetMapping("/updatePatientPage/{id}")	
    public String showUpdatePatientPage(@PathVariable("id") int id, Model model) {
    	Optional<Patient> temp=patientDAO.findById(id);
    	Patient patient=temp.get();
    	model.addAttribute("patient",patient);
    	return "/pages/patient_form";
    }

    @GetMapping("/deletePatient/{id}")
	public String deletePatient(@PathVariable("id") int id) {
		patientDAO.deleteById(id);
		return "redirect:/patient";
	}


}
