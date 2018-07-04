package com.checkin.dao;

import com.checkin.entity.Checkin;
import com.checkin.entity.CheckinExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckinMapper {
    long countByExample(CheckinExample example);

    int deleteByExample(CheckinExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Checkin record);

    int insertSelective(Checkin record);

    List<Checkin> selectByExample(CheckinExample example);

    Checkin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Checkin record, @Param("example") CheckinExample example);

    int updateByExample(@Param("record") Checkin record, @Param("example") CheckinExample example);

    int updateByPrimaryKeySelective(Checkin record);

    int updateByPrimaryKey(Checkin record);
}