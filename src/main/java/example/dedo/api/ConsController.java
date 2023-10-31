package example.dedo.api;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import example.dedo.PDF.fiche_cons;
import example.dedo.dao.ConsDAO;
import example.dedo.dao.PatientDao;
import example.dedo.model.Consultation;
import example.dedo.model.Patient;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ConsController {
    
    @Autowired
    private ConsDAO consDAO;
    
    @GetMapping("/consPageAPI")
    public List<Consultation> ConsPageAPI(Model model){
        return consDAO.findAll();
    }

    @GetMapping("/cons")
    public String ConsPage(Model model){
        model.addAttribute("consList",consDAO.findAll());
        return "/pages/cons_table";
    }

    @GetMapping("/saveConsPage")
 	public String saveConsPage( Model model) {
        Consultation cons= new Consultation();
        model.addAttribute("cons",cons);
 	    return "/pages/cons_form";
        
 	}

    @PostMapping("/saveConsAPI")
    public Consultation saveConsAPI( Consultation cons) {
        return consDAO.save(cons);
    }


    @PostMapping("/saveCons")
    public String saveCons( @ModelAttribute("patient") Consultation cons) {
        if (cons.getDescription().equals("")){
            return "/pages/cons_table";
        }
        consDAO.save(cons);
        if(cons.getResConsultation().equals("controle")){
            //consDAO.save(cons);
            System.out.println("khedmat");
            return "redirect:/saveRDVPage";
        }else if(cons.getResConsultation().equals("examination")){
            //consDAO.save(cons);
            System.out.println("kadoz examination");
            return "redirect:/amb";
        }
        //consDAO.save(cons);
        return "redirect:/cons";

    }
    @GetMapping("/updateConsPage2/{id}")
    public String showUpdateConsPage2(@PathVariable("id") int id, Model model) {
        Optional<Consultation> temp=consDAO.findById(id);
        Consultation cons=temp.get();
        model.addAttribute("cons",cons);
        return "/pages/cons_form";
    }
    @PutMapping("/updateConsAPI")
    public Consultation UpdateRDVAPI( @RequestBody Consultation cons) {
        //rdv.setId_rdv(id);
    	return consDAO.save(cons);
    	
    }


    @GetMapping("/updateConsPage/{id}")
    public String showUpdateConsPage(@PathVariable("id") int id, Model model) {
        Optional<Consultation> temp=consDAO.findById(id);
        Consultation cons=temp.get();
        model.addAttribute("cons",cons);
        if(cons.getResConsultation().equals("controle")){
            //consDAO.save(cons);
            System.out.println("khedmat");
            return "redirect:/saveRDVPage";
        }
        return "/pages/cons_form";
    }
    @GetMapping("/deleteCons/{id}")
	public String deleteCons(@PathVariable("id") int id) {
		consDAO.deleteById(id);
		return "redirect:/cons";
	}
    @Autowired
    PatientDao pdao;
    @GetMapping("/export-pdf/{id}")
    public void generatePdfFile(HttpServletResponse response,@PathVariable("id") int id) throws DocumentException, IOException 
    {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        List<Patient> patient = pdao.findAll();
        fiche_cons fiche = new fiche_cons();
        fiche.generate(patient, response);
    }
    
}
