package com.example.properly.tenant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

interface TenantRepository extends JpaRepository<Tenant, Long>, PagingAndSortingRepository<Tenant, Long> {}