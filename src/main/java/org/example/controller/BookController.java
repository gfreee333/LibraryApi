package org.example.controller;

import org.example.dao.LibraryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/library/book")
public class BookController {
    private final LibraryDAO libraryDAO;
    @Autowired
    public BookController(LibraryDAO libraryDAO) {
        this.libraryDAO = libraryDAO;
    }
    @GetMapping()
    public String indexBook(Model model){
        model.addAttribute("books", libraryDAO.getFullBock());
        return "library/book/book";
    }
    @GetMapping("/{book_id}")
    public String showBook(@PathVariable("book_id") int book_id, Model model){
        model.addAttribute("books", libraryDAO.showBock(book_id));
        return "library/book/showBook";
    }
}
