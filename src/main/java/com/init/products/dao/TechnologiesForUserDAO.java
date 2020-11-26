package com.init.products.dao;

import com.init.products.entitys.TechnologiesForUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologiesForUserDAO extends JpaRepository<TechnologiesForUser, Long> {
}
