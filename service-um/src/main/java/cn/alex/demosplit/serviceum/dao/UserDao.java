package cn.alex.demosplit.serviceum.dao;


import cn.alex.demosplit.serviceum.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
}