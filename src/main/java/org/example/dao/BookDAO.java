package org.example.dao;

import org.example.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> getFullBock(){
        return jdbcTemplate.query("select* from book",
                new BeanPropertyRowMapper<>(Book.class));
    }
    public Book showBock(int book_id){
        return jdbcTemplate.query("select *from book where book_id =?",
                        new Object[]{book_id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }
    public void saveBook(Book book){
        jdbcTemplate.update("insert into book(book_name, Author, year) values (?,?,?)",
                book.getBookName(),
                book.getAuthor(),
                book.getYear());
    }
    //Для изминенитя person_id то етсь для открепления книги от пользователя
    public void editBook(int id, Book book){
        jdbcTemplate.update("update book set book_name=?, author=?,year=?,person_id=? where book_id =?",
                book.getBookName(), book.getAuthor(), book.getYear(), book.getPerson_id(),id);
    }
    public void updateBook(int id, Book book){
        jdbcTemplate.update("update book set person_id=? where id =?",
                book.getPerson_id(),id);
    }
    public void deleteBook(int id){
        jdbcTemplate.update("delete from book where book_id = ?",id);
    }

}
