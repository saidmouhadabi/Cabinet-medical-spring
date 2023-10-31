package example.dedo.dao;

import example.dedo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    @Query(value = "select u from User u  where u.username='said'")
    User findByUsername(String username);

   // @Modifying
    //@Query(value = "update User  set password=?1 where username='said'")
    //public void resetPassword(String password);

}
