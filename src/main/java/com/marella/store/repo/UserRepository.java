package com.marella.store.repo;

import com.marella.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

     void deleteById(Long Id);
}
