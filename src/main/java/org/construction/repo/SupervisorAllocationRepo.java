package org.construction.repo;

import org.construction.model.EngineerAllocation;
import org.construction.model.SiteMaster;
import org.construction.model.SupervisorAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorAllocationRepo extends JpaRepository<SupervisorAllocation, Long> {
    SupervisorAllocation findBySiteMaster(SiteMaster siteMaster);
}
