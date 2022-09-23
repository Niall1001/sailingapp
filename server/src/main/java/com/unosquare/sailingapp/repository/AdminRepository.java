package com.unosquare.sailingapp.repository;

import com.unosquare.sailingapp.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
