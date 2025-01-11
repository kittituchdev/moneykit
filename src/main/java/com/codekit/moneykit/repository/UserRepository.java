package com.codekit.moneykit.repository;

import com.codekit.moneykit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
