package com.example.properly.lease;

import com.example.properly.property.Property;
import com.example.properly.tenant.Tenant;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
public class Lease implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    private Double rentAmount;
    private Date startDate;
    private Date endDate;
    public enum LeaseStatus {
        ACTIVE,
        EXPIRED,
        TERMINATED,
        EARLY_TERMINATION,
        PENDING
    };

    @Enumerated(EnumType.STRING)
    private LeaseStatus leaseStatus = LeaseStatus.PENDING;

    public Lease() {}

    public Lease(Tenant tenant, Property property, Double rentAmount, Date startDate, Date endDate, LeaseStatus leaseStatus) {
        this.tenant = tenant;
        this.property = property;
        this.rentAmount = rentAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaseStatus = leaseStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Double getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(Double rentAmount) {
        this.rentAmount = rentAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public LeaseStatus getLeaseStatus() {
        return leaseStatus;
    }

    public void setLeaseStatus(LeaseStatus leaseStatus) {
        this.leaseStatus = leaseStatus;
    }
}
