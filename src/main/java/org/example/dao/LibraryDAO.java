package org.example.dao;

import org.example.entity.Book;
import org.example.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public LibraryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Получение данных из БД и отображение их на странице
    public List<Person> getFullPerson(){
        return jdbcTemplate.query("select * from person",
                new BeanPropertyRowMapper<>(Person.class));
    }
    public List<Book> getFullBock(){
        return jdbcTemplate.query("select* from book",
                new BeanPropertyRowMapper<>(Book.class));
    }
    public Person showPerson(int id){
    return jdbcTemplate.query("select *from person where id = ?",
            new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
            .stream().findAny().orElse(null);
    }
    public Book showBock(int book_id){
        return jdbcTemplate.query("select *from book where book_id =?",
                new Object[]{book_id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

}
