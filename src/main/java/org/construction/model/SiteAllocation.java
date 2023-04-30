package org.construction.model;

import javax.persistence.*;

@Entity
@Table(name="site_allocation", schema = "site_construction")
public class SiteAllocation {

    @Id
    @SequenceGenerator(name = "site_allocation_id_generator", sequenceName = "site_construction.site_allocation_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "site_allocation_id_generator")
    @Column(name = "site_allocation_id", nullable = false)
    private long siteAllocationId;
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

    public long getSiteAllocationId() {
        return siteAllocationId;
    }

    public void setSiteAllocationId(long siteAllocationId) {
        this.siteAllocationId = siteAllocationId;
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
