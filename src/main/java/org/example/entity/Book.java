package org.example.entity;


import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Book {
    @Size(min = 4, max = 100, message = "Book name is not correct!")
    private String bookName;
    @Size(min = 4, max = 100, message = "Author name is not correct!")
    private String author;
    @Min(value = 0, message = "Wrong year!")
    private int year;
    public Book(String bookName, String author, int year){
        this.bookName = bookName;
        this.author = author;
        this.year = year;
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
