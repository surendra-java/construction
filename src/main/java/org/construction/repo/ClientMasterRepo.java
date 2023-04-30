package org.construction.repo;

import org.construction.model.ClientMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientMasterRepo extends JpaRepository<ClientMaster, Long> {

    @Query("SELECT c FROM ClientMaster c WHERE c.clientId NOT IN (SELECT ca.clientMaster.clientId FROM ClientAllocation ca)")
    List<ClientMaster> findAllByNotInAllocation();

}
