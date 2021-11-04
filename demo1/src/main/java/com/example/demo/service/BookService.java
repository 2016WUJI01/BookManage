package com.example.demo.service;

import com.example.demo.bean.Book;


import java.util.List;

public interface BookService {
    /**
     * add new book into book table
     *
     * @param book
     */
    boolean addBook(Book book);

    /**
     * update book info
     *
     * @param book
     */
    boolean updateBook(Book book);

    /**
     * delete book
     *
     * @param id
     */
    boolean deleteBook(int id);

    /**
     * find book by bookname
     * @param bookname
     * @return
     */
    Book findByBookname(String bookname);

    /**
     * find all book
     * @return
     */
    List<Book> findAllBook();
}
