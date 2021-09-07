package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.LanUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the LanUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LanUserRepository extends JpaRepository<LanUser, Long> {}
