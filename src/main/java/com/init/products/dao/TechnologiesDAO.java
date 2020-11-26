package com.init.products.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.products.entitys.Technologies;

public interface TechnologiesDAO extends JpaRepository<Technologies, Long> {

}

