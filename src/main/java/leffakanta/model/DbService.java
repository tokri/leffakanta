package leffakanta.model;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

// DbService handles all database requests for the rest of the models
@Service
public class DbService {
    private static DataSource dataSource;
    
    // connect datasource for database automatically
    @Autowired
    private void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
    }    

    // get jdbcTemplate for handling db connection and sql injection attempts
    private static JdbcTemplate getJdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
    
    // overload queryForObject to accept only one arg-value
    public static <T> T queryForObject(String sql, Object arg, Class mappedClass){
        return queryForObject(sql, new Object[] { arg }, mappedClass);
    }
    
    // query for list
    // sql = SQL query as a string
    // args = objects for filling ? -fields on SQL string
    // mappedClass = class to use for mapping the querys results
    public static <T> T queryForObject(String sql, Object[] args, Class mappedClass){
        Object object = null;
        try {
            object = getJdbcTemplate().queryForObject(sql, args,
                                new BeanPropertyRowMapper(mappedClass));
        } catch (Exception e){}        
        return (T) object;
    }
    
    // overload queryForList to accept only one arg-value
    public static <T> List<T> queryForList(String sql, Object arg, Class mappedClass){
        return queryForList(sql, new Object[] { arg }, mappedClass);
    }

    // query for listed classes
    public static <T> List<T> queryForList(String sql, Object[] args, Class mappedClass){
        List<T> list = null;
        try {
            list = getJdbcTemplate().query(sql, args,
                            new BeanPropertyRowMapper(mappedClass));
        } catch (Exception e){}        
        return list;
    }

    // overload queryForInt to accept only one arg-value
    public static int queryForInt(String sql, Object arg){
        return queryForInt(sql, new Object[] { arg });
    }

    // query for ints
    public static int queryForInt(String sql, Object[] args){
        int retVal = -1;
        try {
            retVal = getJdbcTemplate().queryForInt(sql, args);
        } catch (Exception e){}
        return retVal;
    }
    
    // overload update to accept only one arg-value
    public static int update(String sql, Object arg){
        return update(sql, new Object[] { arg });
    }
    
    // update
    public static int update(String sql, Object[] args){
        int retVal = -1;
        try {
            retVal = getJdbcTemplate().update(sql, args);
        } catch (Exception e){}
        return retVal;
    }
}