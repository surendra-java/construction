package org.construction.repo;

import org.construction.model.ClientProgressStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientProgressStatusRepo extends JpaRepository<ClientProgressStatus, Long> {
}
