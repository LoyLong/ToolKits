package org.loy.hibernate.dao.impl;

import java.util.List;

import org.loy.hibernate.dao.IUserDao;
import org.loy.hibernate.pojo.User;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class UserDaoImpl extends HibernateDaoSupport implements IUserDao {

	public int validateUser(User user) {

		List<User> result = getHibernateTemplate().findByExample(user);
		
		System.out.println("[UserDaoImpl].[validateUser].result = " + result);
		
		if (null == result || result.isEmpty()) {
			return 1;
		}
		
		/*
		result = getHibernateTemplate().findByExample(user);
		
		if (null == result || result.isEmpty()) {
			return 1;
		}
		*/
		
		return 0;
	}

}
