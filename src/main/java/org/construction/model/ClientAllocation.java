package org.construction.model;

import javax.persistence.*;

@Entity
@Table(name="client_allocation", schema = "site_construction")
public class ClientAllocation {

    @Id
    @SequenceGenerator(name = "client_allocation_id_generator", sequenceName = "site_construction.client_allocation_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_allocation_id_generator")
    @Column(name = "client_allocation_id", nullable = false)
    private long clientAllocationId;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="client_id", referencedColumnName="client_id")
    })
    private ClientMaster clientMaster;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="site_master_id", referencedColumnName="site_master_id")
    })
    private SiteMaster siteMaster;

    public long getClientAllocationId() {
        return clientAllocationId;
    }

    public void setClientAllocationId(long clientAllocationId) {
        this.clientAllocationId = clientAllocationId;
    }

    public ClientMaster getClientMaster() {
        return clientMaster;
    }

    public void setClientMaster(ClientMaster clientMaster) {
        this.clientMaster = clientMaster;
    }

    public SiteMaster getSiteMaster() {
        return siteMaster;
    }

    public void setSiteMaster(SiteMaster siteMaster) {
        this.siteMaster = siteMaster;
    }
}
