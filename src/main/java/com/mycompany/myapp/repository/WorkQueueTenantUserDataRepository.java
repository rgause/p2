package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.WorkQueueTenantUserData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkQueueTenantUserData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkQueueTenantUserDataRepository extends JpaRepository<WorkQueueTenantUserData, Long> {}
