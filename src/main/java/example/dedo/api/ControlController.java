package example.dedo.api;


import java.util.Optional;

import example.dedo.dao.ControlDAO;
import example.dedo.model.Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ControlController {

    @Autowired
    private ControlDAO controleDAO;

    @GetMapping("/control")
    public String ControlPage(Model model){
        model.addAttribute("controlList",controleDAO.findAll());
        return "/pages/control_table";
    }

    @GetMapping("/saveControlPage")
    public String saveControlPage(Model model) {
        Control control = new Control() ;
        model.addAttribute("control",control);
        return "/pages/control_form";
    }

    @PostMapping("/saveControl")
    public String saveControl(@ModelAttribute("control") Control control) {
        controleDAO.save(control);
        if (control.getResultat_Control().equals("")){
            return "/pages/control_table";
        }
        controleDAO.save(control);
        if(control.getResultat_Control().equals("controle")){
            //consDAO.save(cons);
            System.out.println("khedmat");
            return "redirect:/saveRDVPage";
        }else if(control.getResultat_Control().equals("examination")){
            //consDAO.save(cons);
            System.out.println("kadoz examination");
            return "redirect:/amb";
        }
        return "redirect:/control";
    }

    @GetMapping("/updateControlUpdate/{id}")
    public String showUpdateControlPage(@PathVariable("id") int id, Model model) {
        Optional<Control> temp=controleDAO.findById(id);
        Control control=temp.get();
        model.addAttribute("control",control);
        return "/pages/control_form";
    }

    @GetMapping("/deleteControl/{id}")
    public String deleteRDV(@PathVariable("id") int id) {
        controleDAO.deleteById(id);
        return "/pages/control_table";
    }

}