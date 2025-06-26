package com.example.properly.lease;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

interface LeaseRepository extends JpaRepository<Lease, Long>, PagingAndSortingRepository<Lease, Long> {}