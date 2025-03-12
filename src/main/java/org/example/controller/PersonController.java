package org.example.controller;

import org.example.dao.PersonDAO;
import org.example.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/library/person")
public class PersonController {
    private final PersonDAO personDAO;
    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @GetMapping
    public String indexPerson(Model model){
        model.addAttribute("people", personDAO.getFullPerson());
        return "library/person/index";
    }
    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("people", personDAO.showPerson(id));
        return "library/person/showPerson";
    }
    @GetMapping("/newPerson")
    public String newPersonMet(Model model){
        model.addAttribute("people",new Person());
        return "/library/person/newPerson";
    }
    @PostMapping
    public String createPerson(@ModelAttribute("people") @Valid Person person){
        personDAO.savePerson(person);
        return "redirect:/library/person";
    }
}
