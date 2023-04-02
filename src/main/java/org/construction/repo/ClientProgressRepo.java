package org.construction.repo;

import org.construction.model.ClientMaster;
import org.construction.model.ClientProgress;
import org.construction.model.ClientProgressMaster;
import org.construction.model.ClientProgressStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientProgressRepo extends JpaRepository<ClientProgress, Long> {
    List<ClientProgress> findByClientMaster(ClientMaster clientMaster);

    boolean existsByClientMasterAndClientProgressMasterAndClientProgressStatus(ClientMaster clientMaster, ClientProgressMaster clientProgressMaster, ClientProgressStatus clientProgressStatus);

    List<ClientProgress> findByClientMasterClientId(Long clientId);
}
