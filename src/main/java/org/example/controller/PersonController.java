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
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person",personDAO.showPerson(id));
        return "/library/person/edit";
    }
    @PatchMapping("/{id}")
    public String editPerson(@ModelAttribute("person") @Valid Person person,
                             @PathVariable("id") int id){
       personDAO.editPerson(id,person);
       return "redirect:/library/person";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.deletePerson(id);
        return "redirect:/library/person";
    }
}
