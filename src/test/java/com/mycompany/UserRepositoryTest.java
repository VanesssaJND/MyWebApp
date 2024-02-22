package com.mycompany;

import com.mycompany.user.User;
import com.mycompany.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired private UserRepository repository;

    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("apparraj@gmail.com");
        user.setPassword("unbarquito");
        user.setFirstName("Andrea");
        user.setLastName("Parra");

        User savedUser =repository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<User>users = repository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user: users
             ) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
        Integer userId = 1;
        Optional<User> optionalUser = repository.findById(userId);
        User user = optionalUser.get();
        user.setPassword("holalola");
        repository.save(user);

        User updateUser = repository.findById(userId).get();
        Assertions.assertThat(updateUser.getPassword()).isEqualTo("holalola");

    }

    @Test
    public void testGet(){
        Integer userId =7;
        Optional<User>optionalUser = repository.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());

    }

    @Test
    public void testDelete(){
        Integer userId = 2;
        repository.deleteById(userId);

        Optional<User>optionalUser = repository.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}


