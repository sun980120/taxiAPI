package com.example.taxi.repository;

import com.example.taxi.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {
    UserInformation findByUserEmail(String userEmail);

    boolean existsByUserEmail(String userEmail);
}
