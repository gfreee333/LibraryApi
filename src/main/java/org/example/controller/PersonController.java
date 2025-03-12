package org.example.controller;

import org.example.dao.LibraryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/library/person")
public class PersonController {
    private final LibraryDAO libraryDAO;
    @Autowired
    public PersonController(LibraryDAO libraryDAO) {
        this.libraryDAO = libraryDAO;
    }
    @GetMapping
    public String indexPerson(Model model){
        model.addAttribute("people", libraryDAO.getFullPerson());
        return "library/person/person";
    }
    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("people", libraryDAO.showPerson(id));
        return "library/person/showPerson";
    }
}
