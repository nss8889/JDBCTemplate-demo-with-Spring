package nss.config;

import nss.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Import(UserRepository.class)
@Configuration
public class BeanConfig {

    @Bean
    protected JdbcTemplate jdbcTemplate(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        //TODO
        // provide correct JDBC connection String according to database
        dataSource.setUrl("jdbc:mysql://localhost:3307/userdb?useSSL=false");
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);

        //TODO
        // provide actual database values
        dataSource.setUsername("db_username");
        dataSource.setPassword("db_password");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
        /*
		Below code can be used for executing a SQL script
		
		InputStream inputStream = getClass().getResourceAsStream("/db-scripts.sql");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder script = new StringBuilder();
        String data = "";
        //data += bufferedReader.readLine();
        while ((data = bufferedReader.readLine()) !=null)
        {
            script.append(data);
            System.out.println("New Line...");
            System.out.println("script : "+script);
        }
        System.out.println("script : "+script);
        //jdbcTemplate.execute(script.toString());*/
        return jdbcTemplate;
    }
}
