package org.loy.mybatis.service;

import org.loy.hibernate.pojo.OrderExecution;

public interface IExecutionService {

    OrderExecution[] selectExecutionById(int id);

}
