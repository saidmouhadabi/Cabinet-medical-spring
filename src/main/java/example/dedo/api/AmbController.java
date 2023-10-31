package example.dedo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import example.dedo.dao.AmbDAO;
import example.dedo.model.Ambulance;

@Controller
public class AmbController {
    
    @Autowired
    private AmbDAO ambDAO;
    
    @GetMapping("/ambPageAPI")
    public List<Ambulance> AmbPageAPI(){
        return ambDAO.findAll();
    }

    @GetMapping("/amb")
    public String ambPage(Model model){
        model.addAttribute("ambList",ambDAO.findAll());
        return "/pages/amb_table";
    }


    @GetMapping("/saveAmbPage")
 	public String saveAmbPage(Model model) {
 		Ambulance amb= new Ambulance();
 		model.addAttribute("amb",amb);
 	 return "add_amb";
 	}

    @PostMapping("/saveAmb")
    public Ambulance saveAmb(@RequestBody Ambulance amb) {
        //amb.setDate_rdv(null);
        //rdvDAO.save(rdv);
        return ambDAO.save(amb);
    }

    @PostMapping("/saveAmbAPI")
    public List<Ambulance> saveAmbAPI(@RequestBody List<Ambulance> ambs) {
        ambs.forEach(e->{
            ambDAO.save(e);
        });
        return ambDAO.findAll();
    }
    
    @PutMapping("/updateAmb")
    public Ambulance showUpdateAmbPage( @RequestBody Ambulance amb) {
        //rdv.setId_rdv(id);
    	return ambDAO.save(amb);
    	
    }

    @GetMapping("/updateAmbPage/{id}")	
    public String showUpdateAmbPage(@PathVariable("id") int id, Model model) {
    	Optional<Ambulance> temp=ambDAO.findById(id);
    	Ambulance amb=temp.get();
    	model.addAttribute("amb",amb);
    	return "update_amb";
    }

    @DeleteMapping("/deleteAmb/{id}")
	public String deleteAmb(@PathVariable("id") int id) {
		ambDAO.deleteById(id);
		return "redirect:/";
	}


    
}