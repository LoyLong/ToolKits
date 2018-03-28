package org.loy.mybatis.mapper;

import org.apache.ibatis.annotations.Select;
import org.loy.hibernate.pojo.OrderExecution;

public interface ExecutionMapper {

    @Select("select * from OES02..AuxExec where OrdNum = #{id}")
    OrderExecution[] selectExecutionById(int id);
    
}
