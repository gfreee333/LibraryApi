package org.example.controller;

import org.example.dao.BookDAO;
import org.example.dao.PersonDAO;
import org.example.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/library/book")
public class BookController {
    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    @GetMapping()
    public String indexBook(Model model){
        model.addAttribute("books", bookDAO.getFullBock());
        return "library/book/index";
    }
    @GetMapping("/{book_id}")
    public String showBook(@PathVariable("book_id") int book_id, Model model){
        model.addAttribute("books", bookDAO.showBock(book_id));
        return "library/book/showBook";
    }
    @GetMapping("/newBook")
    public String newBook(Model model){
        model.addAttribute("book",new Book());
        return "library/book/newBook";
    }
    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book){
        bookDAO.saveBook(book);
        return "redirect:/library/book";
    }
    @DeleteMapping("/{book_id}")
    public String deleteBook(@PathVariable("book_id") int book_id){
        bookDAO.deleteBook(book_id);
        return "redirect:/library/book";
    }
    @GetMapping("/{book_id}/edit")
    public String edit(Model model, @PathVariable("book_id") int book_id){
        model.addAttribute("book",bookDAO.showBock(book_id));
        return "/library/book/edit";
    }
    @PatchMapping("/{book_id}")
    public String editBook(@ModelAttribute("book") @Valid Book book,
                           @PathVariable("book_id") int book_id){
        bookDAO.editBook(book_id, book);
        return "redirect:/library/book";
    }

}
