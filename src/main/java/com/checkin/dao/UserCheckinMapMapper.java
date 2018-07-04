package com.checkin.dao;

import com.checkin.entity.UserCheckinMap;
import com.checkin.entity.UserCheckinMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCheckinMapMapper {
    long countByExample(UserCheckinMapExample example);

    int deleteByExample(UserCheckinMapExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserCheckinMap record);

    int insertSelective(UserCheckinMap record);

    List<UserCheckinMap> selectByExample(UserCheckinMapExample example);

    UserCheckinMap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserCheckinMap record, @Param("example") UserCheckinMapExample example);

    int updateByExample(@Param("record") UserCheckinMap record, @Param("example") UserCheckinMapExample example);

    int updateByPrimaryKeySelective(UserCheckinMap record);

    int updateByPrimaryKey(UserCheckinMap record);
}