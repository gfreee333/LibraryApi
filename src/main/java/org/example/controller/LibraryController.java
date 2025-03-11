package org.example.controller;

import org.example.dao.LibraryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/library")
public class LibraryController {
    private final LibraryDAO libraryDAO;
    @Autowired
    public LibraryController(LibraryDAO libraryDAO){
        this.libraryDAO = libraryDAO;
    }
    @GetMapping
    public String indexBook(Model model){
        model.addAttribute("book", libraryDAO.getFullBock());
        return "/library/book";
    }
    @GetMapping
    public String indexPerson(Model model){
        model.addAttribute("person", libraryDAO.getFullPerson());
        return "/library/person";
    }

}
