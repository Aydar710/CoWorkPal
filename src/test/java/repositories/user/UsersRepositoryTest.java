package repositories.user;

import models.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repositories.DataSourceSingleton;

import java.util.List;

import static org.junit.Assert.*;

public class UsersRepositoryTest {

    private UsersRepository usersRepository;

    private User EXPECTED_USER_WIITH_ID_1 = User.builder()
            .id(1)
            .name("userName")
            .passwordHash("pass")
            .build();


    @Before
    public void setUp() throws Exception {
        usersRepository = new UsersRepository(DataSourceSingleton.getDataSource());
    }

    @Test
    public void findAllTest() {
        List<User> userList = usersRepository.findAll();
        assertTrue(userList.size() != 0);
    }

    @Test
    public void insertTest() {
        User user = User.builder()
                .name("name1")
                .passwordHash("123")
                .build();

        usersRepository.save(user);
    }

    @Test
    public void find(){
        User actualUser = usersRepository.find(1);
        assertEquals(EXPECTED_USER_WIITH_ID_1, actualUser);
    }
}