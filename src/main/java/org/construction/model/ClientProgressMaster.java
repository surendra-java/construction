package org.construction.model;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "client_progress_master" database table.
 * 
 */
@Data
@Entity
@Table(name="client_progress_master", schema = "site_construction")
@NamedQuery(name="ClientProgressMaster.findAll", query="SELECT c FROM ClientProgressMaster c")
public class ClientProgressMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="client_progress_master_id")
	private long clientProgressMasterId;

	@Column(name="client_progress_desc")
	private String clientProgressDesc;

	@Column(name="client_progress_name")
	private String clientProgressName;

	//bi-directional many-to-one association to ClientProgress
	@OneToMany(mappedBy="clientProgressMaster")
	private List<ClientProgress> clientProgresses;

	public ClientProgressMaster() {
	}

	public ClientProgress addClientProgress(ClientProgress clientProgress) {
		getClientProgresses().add(clientProgress);
		clientProgress.setClientProgressMaster(this);

		return clientProgress;
	}

	public ClientProgress removeClientProgress(ClientProgress clientProgress) {
		getClientProgresses().remove(clientProgress);
		clientProgress.setClientProgressMaster(null);

		return clientProgress;
	}

}