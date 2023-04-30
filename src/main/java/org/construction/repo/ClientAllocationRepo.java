package org.construction.repo;

import org.construction.model.ClientAllocation;
import org.construction.model.SiteAllocation;
import org.construction.model.SiteMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientAllocationRepo extends JpaRepository<ClientAllocation, Long> {
    void deleteBySiteMaster(SiteMaster siteMaster);

    ClientAllocation findBySiteMaster(SiteMaster siteMaster);
}
