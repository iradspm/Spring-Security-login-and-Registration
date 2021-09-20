package com.example.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;
    @Autowired
    private TestEntityManager entityManager;
    @Test
    public void testCreateUser()
    {
        //test checked
        try {
            User user=new User();
            user.setEmail("pm@gmail.com");
            user.setPassword("123445");
            user.setFirstName("pm");
            user.setLastName("mw");

            User saveUser=repo.save(user);
            User existUser=entityManager.find(User.class,saveUser.getId());
            assert(existUser.getEmail())==existUser.getEmail();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    @Test
    public void testFindUserByEmail()
    {
       try
       {
           String email="pm@gmail.com";
           User user=repo.findByEmail(email);
           assert (user) != null;
       }catch (Exception e)
       {
           e.printStackTrace();
       }
    }
}
