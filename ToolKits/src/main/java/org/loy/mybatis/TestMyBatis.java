package org.loy.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;
import org.loy.hibernate.pojo.OrderExecution;
import org.loy.mybatis.mapper.ExecutionMapper;
import org.loy.mybatis.service.IExecutionService;

public class TestMyBatis {

    public static void main(String[] args) {
        new TestMyBatis().testWithOutXMLConfig();
    }

    @Test
    public void testWithXMLConfig() {
        System.out.println("[TestMyBatis].[testWithXMLConfig].in");

        SqlSession session = null;
        try {
            String resource = "config/mybatis/mybatis.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            session = sqlSessionFactory.openSession();
            IExecutionService mapper = session.getMapper(IExecutionService.class);
            OrderExecution[] exec = mapper.selectExecutionById(1);
            System.out.println("result count = " + exec.length);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != session) {
                session.close();
            }
        }
    }

    @Test
    public void testWithXMLConfig2() {
        System.out.println("[TestMyBatis].[testWithXMLConfig2].in");

        SqlSession session = null;
        try {
            String resource = "config/mybatis/mybatis.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            session = sqlSessionFactory.openSession();

            List<Object> result = session.selectList("org.loy.mybatis.service.IExecutionService.selectExecutionById", 1);
            System.out.println("result count = " + result.size());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != session) {
                session.close();
            }
        }
    }

    @Test
    public void testWithOutXMLConfig() {
        System.out.println("[TestMyBatis].[testWithOutXMLConfig].in");

        SqlSession session = null;
        try {
            DataSourceFactory dataSrcFactory = new PooledDataSourceFactory();
            Properties props = new Properties();
            props.put("driver", "com.sybase.jdbc3.jdbc.SybDriver");
            props.put("url", "jdbc:sybase:Tds:HK000TKO16077.hk.xxx:10100/OES02");
            props.put("username", "sysgrbsmq");
            props.put("password", "xbsemfz2");

            dataSrcFactory.setProperties(props);

            DataSource dataSource = dataSrcFactory.getDataSource();
            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment("development", transactionFactory, dataSource);
            Configuration configuration = new Configuration(environment);
            configuration.addMapper(ExecutionMapper.class);

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

            session = sqlSessionFactory.openSession();
            ExecutionMapper mapper = session.getMapper(ExecutionMapper.class);
            OrderExecution[] exec = mapper.selectExecutionById(1);
            System.out.println("result count = " + exec.length);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != session) {
                session.close();
            }
        }
    }


}
