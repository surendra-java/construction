package org.construction.model;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "client_master" database table.
 * 
 */
@Data
@Entity
@Table(name="client_master", schema = "site_construction")
@NamedQuery(name="ClientMaster.findAll", query="SELECT c FROM ClientMaster c")
public class ClientMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CLIENT_ID_GENERATOR", sequenceName = "site_construction.client_master_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENT_ID_GENERATOR")
	@Column(name="client_id")
	private long clientId;

	@Column(name="client_address")
	private String clientAddress;

	@Column(name="client_mob_nbr")
	private String clientMobNbr;

	@Column(name="client_name")
	private String clientName;

	@Lob
	@Column(name = "client_photo")
	private byte[] clientPhoto;
	//bi-directional many-to-one association to ClientProgress
	@OneToMany(mappedBy="clientMaster")
	private List<ClientProgress> clientProgresses;

	@OneToMany(mappedBy="clientMaster")
	private List<SiteAllocation> siteAllocations;

	public ClientMaster() {
	}


	public ClientProgress addClientProgress(ClientProgress clientProgress) {
		getClientProgresses().add(clientProgress);
		clientProgress.setClientMaster(this);

		return clientProgress;
	}

	public ClientProgress removeClientProgress(ClientProgress clientProgress) {
		getClientProgresses().remove(clientProgress);
		clientProgress.setClientMaster(null);

		return clientProgress;
	}

}