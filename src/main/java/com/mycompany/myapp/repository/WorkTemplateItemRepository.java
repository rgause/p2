package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.WorkTemplateItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkTemplateItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkTemplateItemRepository extends JpaRepository<WorkTemplateItem, Long> {}
