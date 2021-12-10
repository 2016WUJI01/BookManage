package com.example.demo.dao;

import com.example.demo.bean.Bookorder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookorderDao {

    @Select("select * from bookorder")
    List<Bookorder> findAllBookorder();

    @Insert("insert into bookorder(id,adminid,userid,bookid,number,date,situation) values(#{id},#{adminid},#{userid},#{bookid},#{number},#{date},#{situation})")
    void addBookorder(Bookorder bookorder);

    @Delete("delete from bookorder where id = #{id}")
    void deleteBookorder(Bookorder bookorder);

    @Update("update bookorder set adminid=#{adminid},userid=#{userid},bookid=#{bookid},number=#{number},date=#{date},situation=#{situation} where  id=#{id}")
    void updateBookorder(Bookorder bookorder);

}
