package example.dedo.api;

import example.dedo.dao.AmbDAO;
import example.dedo.dao.InfDAO;
import example.dedo.dao.MedecinDAO;
import example.dedo.model.Ambulance;
import example.dedo.model.Infirmier;
import example.dedo.model.Medecin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class UrgenceController {
    @Autowired
    private InfDAO infDAO;
    @Autowired
    private MedecinDAO medcDAO;
    @Autowired
    private AmbDAO ambDAO;

    private int amb_tele;
    private int medecin_tele;
    private int inf_tele;

    @GetMapping("/ambCall/{id}")
    public String ambCall(@PathVariable("id") int id) {
        //amb = ambDAO.findById(id);

        return "redirect:/medc";
    }

    @GetMapping("/medcCall/{id}")
    public String medcCall(@PathVariable("id") int id) {
        medecin_tele = medcDAO.medcTele(id);

        return "redirect:/inf";
    }

    @GetMapping("/infCall/{id}")
    public String infCall(@PathVariable("id") int id) {
        inf_tele = infDAO.infTele(id);

        return "redirect:/urgentCall";
    }

    @GetMapping("/urgentCall")
    public String urgentCall(Model model) {
        model.addAttribute("amb",ambDAO);
        model.addAttribute("inf",inf_tele);
        model.addAttribute("medecin",medecin_tele);

        return "/pages/urgency_table";
    }






}
