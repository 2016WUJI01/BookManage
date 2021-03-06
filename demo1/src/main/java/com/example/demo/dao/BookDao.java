package com.example.demo.dao;

import com.example.demo.bean.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookDao {
    /**
     * add new book into book table
     *
     * @param book
     */
    @Insert("insert into book(id,bookname,supplier,price,reserve) values (#{id},#{bookname},#{supplier},#{price},#{reserve})")
    void addBook(Book book);

    /**
     * update book info
     *
     * @param book
     */
    @Update("update book set bookname=#{bookname}, supplier=#{supplier}, price=#{price}, reserve=#{reserve} where id=#{id}")
    void updateBook(Book book);

    /**
     * delete book
     *
     * @param book
     */
    @Delete("delete from book where id=#{id}")
    void deleteBook(Book book);

    /**
     * find all book
     *
     * @return
     */
    @Select("select * from book")
    List<Book> findAllBook();

}
