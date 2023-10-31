
package example.dedo.api;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
// import java.util.Collection;
// import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import example.dedo.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;

import example.dedo.dao.rdvDao;
import example.dedo.model.rdv;
// import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class rdvController {
    
    @Autowired
    private rdvDao rdvDAO;
    
    @GetMapping("/rdvPageAPI")
    public List<rdv> RDVPageAPI(Model model){
        return rdvDAO.findAll();
    }

    @GetMapping("/test1")
    public String test(@RequestBody rdv rdv){
       // Collection<rdv> data = new ArrayList<>();
       Date data = rdv.getDate_rdv();
       String horaire = rdv.getHoraire();
       int id_rdv = rdv.getId_rdv();
       if(rdvDAO.checkDATE(data,horaire,id_rdv).isEmpty()){
        return "inserted";
       }
        return "impossible";
    }

    
    
    @GetMapping("/rdv")
    public String RDVPage(Model model){
        model.addAttribute("rdvList",rdvDAO.findAll());
        return "/pages/rdv_table";
    }


    @GetMapping("/saveRDVPage")
 	public String saveRDVPage(Model model,String msg) {
 		rdv rdv= new rdv();
        model.addAttribute("rdv",rdv);
        model.addAttribute("msg", msg);
        LocalDate today = LocalDate.now(); // Get the current date
        model.addAttribute("today", today);
        model.addAttribute("disabledDates",rdvDAO.dates());
 	    return "/pages/rdv_form";
 	}

    @PostMapping("/saveRDVAPI")
    public rdv saveRDVAPI(@RequestBody rdv rdv) {
        //rdv.setDate_rdv(null);
        //rdvDAO.save(rdv);
        return rdvDAO.save(rdv);
    }

    @PostMapping("/saveRDVSAPI")
    public List<rdv> saveRDVSAPI(@RequestBody List<rdv> rdvs) {
        //rdv.setDate_rdv(null);
        //rdvDAO.save(rdv);
        rdvs.forEach(e->{
            rdvDAO.save(e);
        });
        return rdvDAO.findAll();
        
    }

    @PostMapping("/saveRDV")
    public String saveRDV(@ModelAttribute("rdv") rdv rdv,Model model) {
        Date data = rdv.getDate_rdv();
        String horaire = rdv.getHoraire();
        int id_rdv = rdv.getId_rdv();
        if(rdvDAO.checkDATE(data,horaire,id_rdv).isEmpty()){
            String nameOfPatient;
            nameOfPatient=rdv.getPatient().getFull_name();
            Patient p =  rdvDAO.findPatinetByFullName(nameOfPatient);
            rdv.setPatient(p);
            rdvDAO.save(rdv);
            return "redirect:/rdv";
        }else{
            String msg;
            msg = "L'horaire ou la date sélectionné est dèjà réservé, veuillez selectionner une date ultérieure !";
            return saveRDVPage(model,msg);
        }
    }
    
    @PutMapping("/updateRDVAPI")
    public rdv showUpdateRDVPageAPI( @RequestBody rdv rdv) {
        //rdv.setId_rdv(id);
    	return rdvDAO.save(rdv);
    	
    }

    @GetMapping("/updateRDVPage/{id}")	
    public String showUpdateRDVPage(@PathVariable("id") int id, Model model,String msg) {
    	Optional<rdv> temp=rdvDAO.findById(id);
    	rdv rdv=temp.get();
    	model.addAttribute("rdv",rdv);
        model.addAttribute("msg", msg);
    	return "/pages/rdv_form";
    }

    @PostMapping("/saveRDVUpdate")
    public String saveRDVUpdate(@ModelAttribute("rdv") rdv rdv,Model model) {
        Date data = rdv.getDate_rdv();
        String horaire = rdv.getHoraire();
        int id_rdv = rdv.getId_rdv();
        if(rdvDAO.checkDATE(data,horaire,id_rdv).isEmpty()){
            rdvDAO.save(rdv);
            return "redirect:/rdv";
        }else{
            String msg;
            msg = "L'horaire ou la date sélectionné est dèjà réservé, veuillez selectionner une date ultérieure !";
            return showUpdateRDVPage(rdv.getId_rdv(),model,msg);
        }
    }


    @GetMapping("/deleteRDV/{id}")
	public String deleteRDV(@PathVariable("id") int id) {
		rdvDAO.deleteById(id);
		return "redirect:/rdv";
	}
    
    

    
}
