package com.skillswapcomunity.skillswapcomunity.repository;

import com.skillswapcomunity.skillswapcomunity.model.Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestsRepository extends JpaRepository<Requests, Long> {
}
