package example.dedo.api;

import example.dedo.EmailSenderService;
import example.dedo.dao.UserDAO;
import example.dedo.dao.rdvDao;
import example.dedo.model.User;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.SecureRandom;
import java.util.UUID;

@Controller
public class LoginController {
    // @RequestMapping("/login")
    // public String loginPage(Model model) {
    //     return "auth-login";
    // }
    User u;
    @Autowired
    private EmailSenderService senderService;
    @Autowired
    private UserDAO userdao;
@Autowired
    private rdvDao rdvDao;

    @RequestMapping("/test")
    public String test(Model model) {
        return "auth-login";
    }
    public static String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        String password = UUID.randomUUID().toString().replace("-", "");
        return password.substring(0,4);
    }
    @RequestMapping("/forgot")
    public String triggerMail()  {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pass = generateRandomPassword();
        String password = bCryptPasswordEncoder.encode(pass);
        //userdao.resetPassword(password);
        userdao.findByUsername("said").toString();
        //userdao.findByUsername("said").setPassword(password);
        u=userdao.findByUsername("said");
        u.setPassword(password);
        userdao.save(u);
        System.out.println(password);
        senderService.sendSimpleEmail("said254said@gmail.com",
                "Votre nouveau mot de passe pour ",


                        "Bonjour Mr Administrateur ,\n" +
                        "\n" +
                        "Nous avons généré un nouveau mot de passe pour votre compte sur l'application [nom de l'application]. Voici votre nouveau mot de passe :\n" +
                        "\n" +
                                 "\n\n"+pass +"\n\n"+
                        "Nous vous recommandons de garder votre mot de passe  pour des raisons de sécurité. Vous pouvez le faire en vous connectant à votre compte  "+
                        "\n" +
                        "Si vous avez des questions ou des préoccupations, n'hésitez pas à nous contacter.\n" +
                        "\n" +
                        "Cordialement, ");
        return "forgot";
    }




    @RequestMapping("/home")
    public String loginSubmit(Model model) {
        model.addAttribute("rdvList",rdvDao.todayRDV());
        System.out.println("dazet fonction rdvToDay");
        return "/pages/landing-page";
    }

}
