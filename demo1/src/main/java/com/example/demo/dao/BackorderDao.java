package com.example.demo.dao;

import com.example.demo.bean.Backorder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BackorderDao {
    /**
     * find all backorder
     *
     * @return
     */
    @Select("select * from backorder")
    List<Backorder> findAllBackorder();

    @Insert("insert into backorder(id,adminid,userid,bookid,number,date,reason) values(#{id},#{adminid},#{userid},#{bookid},#{number},#{date},#{reason})")
    void addBackorder(Backorder backorder);

    @Delete("delete from backorder where id = #{id}")
    void deleteBackorder(Backorder backorder);

    @Update("update backorder set adminid=#{adminid},userid=#{userid},bookid=#{bookid},number=#{number},date=#{date},reason=#{reason} where  id=#{id}")
    void updateBackorder(Backorder backorder);


}
