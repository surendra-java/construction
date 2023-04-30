package org.construction.repo;

import org.construction.model.EngineerAllocation;
import org.construction.model.SiteMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineerAllocationRepo extends JpaRepository<EngineerAllocation, Long> {
    EngineerAllocation findBySiteMaster(SiteMaster siteMaster);
}
