package com.unosquare.sailingapp.repository;

import com.unosquare.sailingapp.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    @Query("SELECT au FROM AppUser au JOIN FETCH au.userAccessStatus WHERE au.emailAddress = ?1")
    Optional<AppUser> findAppUserByEmail(String emailAddress);
}
