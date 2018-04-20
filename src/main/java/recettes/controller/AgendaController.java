package recettes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import recettes.component.User;

import javax.servlet.http.HttpSession;

/**
 * Controller pour la partie Agenda.
 */
@Controller
public class AgendaController {

    @Autowired
    private User user;

    @GetMapping("/agenda")
    public String agenda(Model model, HttpSession session) {

        return "agenda";
    }
}
