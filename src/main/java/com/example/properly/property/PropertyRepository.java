package com.example.properly.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

interface PropertyRepository extends JpaRepository<Property, Long>, PagingAndSortingRepository<Property, Long> {}
