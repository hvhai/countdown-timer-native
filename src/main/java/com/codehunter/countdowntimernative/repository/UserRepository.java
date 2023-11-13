package com.codehunter.countdowntimernative.repository;

import com.codehunter.countdowntimernative.jpa.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<JpaUser, String > {

}
