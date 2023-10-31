package example.dedo.api;


import example.dedo.dao.InfDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfController {

    @Autowired
    private InfDAO infDAO;

    @GetMapping("/inf")
    public String infPage(Model model) {

        model.addAttribute("infList",infDAO.findAll());
        return "/pages/nurse_table";
    }
}
