package example.dedo.api;

import example.dedo.dao.MedecinDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MedecinController {
    @Autowired
    private MedecinDAO medcDAO;

    @GetMapping("/medc")
    public String medPage(Model model) {

        model.addAttribute("medcList",medcDAO.findAll());
        return "/pages/doctor_table";
    }
}
