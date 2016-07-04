package chapter6.permission;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author terryrao on 2016/7/4.
 */
public class JdbcTemplatesUtils {

    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null ) {
            jdbcTemplate = createJdbcTemplate();
        }
        return jdbcTemplate;
    }

   private static JdbcTemplate createJdbcTemplate () {
       DruidDataSource dataSource = new DruidDataSource();
       dataSource.setDriverClassName("com.mysql.jdbc.Driver");
       dataSource.setUrl("");
       dataSource.setUsername("sonar");
       dataSource.setPassword("");
       return new JdbcTemplate(dataSource);

   }
}
