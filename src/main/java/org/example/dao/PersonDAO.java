package org.example.dao;

import org.example.entity.Book;
import org.example.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> getFullPerson(){
        return jdbcTemplate.query("select * from person",
                new BeanPropertyRowMapper<>(Person.class));
    }
    public Person showPerson(int id){
        return jdbcTemplate.query("select *from person where id = ?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    // Получение списка книг у данного пользователя
    public List<Book> getBooksByPersonId(int id) {
        String sql = "SELECT b.* FROM book b " +
                "JOIN person p ON b.person_id = p.id " +
                "WHERE p.id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void savePerson(Person person){
        jdbcTemplate.update("insert into person(fullName,yearOfBirth) values (?,?)",
                person.getFullName(),
                person.getYearOfBirth());
    }
    public void editPerson(int id, Person person){
       jdbcTemplate.update("update person set fullname=?,yearofbirth=? where id =?",
               person.getFullName(),person.getYearOfBirth(),id);
    }
    public void deletePerson(int id){
        jdbcTemplate.update("delete from person where id = ?", id);
    }
}
