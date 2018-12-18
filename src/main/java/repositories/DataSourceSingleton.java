package repositories;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class DataSourceSingleton {

    private static DriverManagerDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUsername("postgres");
            dataSource.setPassword("148868");
            dataSource.setUrl("jdbc:postgresql://localhost:5432/CoWorkPal");
        }
        return dataSource;
    }
}
