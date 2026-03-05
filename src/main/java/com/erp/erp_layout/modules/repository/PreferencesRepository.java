package com.erp.erp_layout.modules.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.erp_layout.modules.domain.UserPreferences;

import java.util.Optional;

@Repository
public interface PreferencesRepository extends JpaRepository<UserPreferences, Long> {
    Optional<UserPreferences> findByUserId(String userId);
}