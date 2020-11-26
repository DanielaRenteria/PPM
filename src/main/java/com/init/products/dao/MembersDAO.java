package com.init.products.dao;

import com.init.products.entitys.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersDAO extends JpaRepository<Members, Long> {

}

