package com.nisumlatam.userapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nisumlatam.userapp.domain.model.Phone;

@Repository
public interface PhoneDao extends JpaRepository<Phone, String> {

}
