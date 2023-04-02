package org.construction.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the "client_progress_status" database table.
 * 
 */
@Data
@Entity
@Table(name="client_progress_status", schema = "site_construction")
@NamedQuery(name="ClientProgressStatus.findAll", query="SELECT c FROM ClientProgressStatus c")
public class ClientProgressStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="client_progress_status_id")
	private long clientProgressStatusId;

	@Column(name="client_progress_status_desc")
	private String clientProgressStatusDesc;

	@Column(name="client_progress_status_name")
	private String clientProgressStatusName;

	//bi-directional many-to-one association to ClientProgress
	@OneToMany(mappedBy="clientProgressStatus")
	private List<ClientProgress> clientProgresses;

	public ClientProgressStatus() {
	}

	public ClientProgress addClientProgresses(ClientProgress clientProgresses) {
		getClientProgresses().add(clientProgresses);
		clientProgresses.setClientProgressStatus(this);
		return clientProgresses;
	}

	public ClientProgress removeClientProgresses1(ClientProgress clientProgresses) {
		getClientProgresses().remove(clientProgresses);
		clientProgresses.setClientProgressStatus(null);
		return clientProgresses;
	}


}