package org.example.entity;


import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Book {
    private int book_id;
    @Size(min = 4, max = 100, message = "Book name is not correct!")
    private String bookName;
    @Size(min = 4, max = 100, message = "Author name is not correct!")
    private String author;
    @Min(value = 0, message = "Wrong year!")
    private int year;
    private int person_id;
    public Book(int book_id,String bookName, String author, int year, int person_id){
        this.book_id = book_id;
        this.bookName = bookName;
        this.author = author;
        this.year = year;
        this.person_id = person_id;
    }
    public Book(){}

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }
}
