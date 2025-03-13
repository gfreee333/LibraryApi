package org.example.dao;

import org.example.entity.Book;
import org.example.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    // Получение информации о пользователе у данной книги.
    public Person getPersonByPersonId(int book_id){
        String sql = "select p.* from person p " +
                "join book b on p.id = b.person_id where book_id =?";
        try {
            return jdbcTemplate.queryForObject(sql,new Object[]{book_id},
                    new BeanPropertyRowMapper<>(Person.class));
        }
        catch (EmptyResultDataAccessException exception){
            return null;
        }
    }
    public List<Person> getAllPerson(){
        return jdbcTemplate.query("select * from person;", new BeanPropertyRowMapper<>(Person.class));
    }
    public void installPerson(int person_id, int book_id){
         jdbcTemplate.update("update book set person_id =? where book_id =?", person_id, book_id);
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
    public void detachPerson(int id){
        jdbcTemplate.update("update book set person_id=null where book_id =?", id);
    }
    public void deleteBook(int id){
        jdbcTemplate.update("delete from book where book_id = ?",id);
    }

}
