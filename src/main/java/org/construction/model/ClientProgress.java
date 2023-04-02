package org.construction.model;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "client_progress" database table.
 * 
 */
@Data
@Entity
@Table(name="client_progress", schema = "site_construction")
@NamedQuery(name="ClientProgress.findAll", query="SELECT c FROM ClientProgress c")
public class ClientProgress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "client_progress_id_generator", sequenceName = "site_construction.client_progress_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_progress_id_generator")
	@Column(name="client_progress_id")
	private long clientProgressId;

	@Column(name="reason")
	private String reason;

	//bi-directional many-to-one association to ClientMaster
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name="client_id", referencedColumnName="client_id")
	})
	private ClientMaster clientMaster;

	//bi-directional many-to-one association to ClientProgressMaster
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name="client_progress_master_id", referencedColumnName="client_progress_master_id")
	})
	private ClientProgressMaster clientProgressMaster;

	//bi-directional many-to-one association to ClientProgressStatus
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name="client_progress_status_id", referencedColumnName="client_progress_status_id")
	})
	private ClientProgressStatus clientProgressStatus;

	//bi-directional many-to-one association to ClientProgressStatus


	public ClientProgress() {
	}

}