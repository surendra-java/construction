package org.construction.repo;

import org.construction.model.SiteAllocation;
import org.construction.model.SiteMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteAllocationRepo extends JpaRepository<SiteAllocation, Long> {
    void deleteBySiteMaster(SiteMaster siteMaster);

    SiteAllocation findBySiteMaster(SiteMaster siteMaster);
}
