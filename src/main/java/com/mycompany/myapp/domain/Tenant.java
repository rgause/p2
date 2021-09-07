package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Tenant.
 */
@Entity
@Table(name = "tenant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tenant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;

    @NotNull
    @Column(name = "provisioned", nullable = false)
    private Boolean provisioned;

    @OneToMany(mappedBy = "tenant")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "tenant", "tenantPropertyKey" }, allowSetters = true)
    private Set<TenantProperty> tenantProperties = new HashSet<>();

    @OneToMany(mappedBy = "tenant")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "workQueueTenantUsers", "lanUser", "tenant" }, allowSetters = true)
    private Set<TenantUser> tenantUsers = new HashSet<>();

    @OneToMany(mappedBy = "tenant")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "workQueueTenantData", "workQueueTenantPreReqs", "tenant" }, allowSetters = true)
    private Set<WorkQueueTenant> workQueueTenants = new HashSet<>();

    @ManyToOne
    private TenantType tenantType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tenant id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Tenant name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return this.active;
    }

    public Tenant active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getProvisioned() {
        return this.provisioned;
    }

    public Tenant provisioned(Boolean provisioned) {
        this.provisioned = provisioned;
        return this;
    }

    public void setProvisioned(Boolean provisioned) {
        this.provisioned = provisioned;
    }

    public Set<TenantProperty> getTenantProperties() {
        return this.tenantProperties;
    }

    public Tenant tenantProperties(Set<TenantProperty> tenantProperties) {
        this.setTenantProperties(tenantProperties);
        return this;
    }

    public Tenant addTenantProperty(TenantProperty tenantProperty) {
        this.tenantProperties.add(tenantProperty);
        tenantProperty.setTenant(this);
        return this;
    }

    public Tenant removeTenantProperty(TenantProperty tenantProperty) {
        this.tenantProperties.remove(tenantProperty);
        tenantProperty.setTenant(null);
        return this;
    }

    public void setTenantProperties(Set<TenantProperty> tenantProperties) {
        if (this.tenantProperties != null) {
            this.tenantProperties.forEach(i -> i.setTenant(null));
        }
        if (tenantProperties != null) {
            tenantProperties.forEach(i -> i.setTenant(this));
        }
        this.tenantProperties = tenantProperties;
    }

    public Set<TenantUser> getTenantUsers() {
        return this.tenantUsers;
    }

    public Tenant tenantUsers(Set<TenantUser> tenantUsers) {
        this.setTenantUsers(tenantUsers);
        return this;
    }

    public Tenant addTenantUser(TenantUser tenantUser) {
        this.tenantUsers.add(tenantUser);
        tenantUser.setTenant(this);
        return this;
    }

    public Tenant removeTenantUser(TenantUser tenantUser) {
        this.tenantUsers.remove(tenantUser);
        tenantUser.setTenant(null);
        return this;
    }

    public void setTenantUsers(Set<TenantUser> tenantUsers) {
        if (this.tenantUsers != null) {
            this.tenantUsers.forEach(i -> i.setTenant(null));
        }
        if (tenantUsers != null) {
            tenantUsers.forEach(i -> i.setTenant(this));
        }
        this.tenantUsers = tenantUsers;
    }

    public Set<WorkQueueTenant> getWorkQueueTenants() {
        return this.workQueueTenants;
    }

    public Tenant workQueueTenants(Set<WorkQueueTenant> workQueueTenants) {
        this.setWorkQueueTenants(workQueueTenants);
        return this;
    }

    public Tenant addWorkQueueTenant(WorkQueueTenant workQueueTenant) {
        this.workQueueTenants.add(workQueueTenant);
        workQueueTenant.setTenant(this);
        return this;
    }

    public Tenant removeWorkQueueTenant(WorkQueueTenant workQueueTenant) {
        this.workQueueTenants.remove(workQueueTenant);
        workQueueTenant.setTenant(null);
        return this;
    }

    public void setWorkQueueTenants(Set<WorkQueueTenant> workQueueTenants) {
        if (this.workQueueTenants != null) {
            this.workQueueTenants.forEach(i -> i.setTenant(null));
        }
        if (workQueueTenants != null) {
            workQueueTenants.forEach(i -> i.setTenant(this));
        }
        this.workQueueTenants = workQueueTenants;
    }

    public TenantType getTenantType() {
        return this.tenantType;
    }

    public Tenant tenantType(TenantType tenantType) {
        this.setTenantType(tenantType);
        return this;
    }

    public void setTenantType(TenantType tenantType) {
        this.tenantType = tenantType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tenant)) {
            return false;
        }
        return id != null && id.equals(((Tenant) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tenant{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", active='" + getActive() + "'" +
            ", provisioned='" + getProvisioned() + "'" +
            "}";
    }
}
