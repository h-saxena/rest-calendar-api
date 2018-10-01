package com.hemant.cal.repo;

import com.hemant.cal.model.AppCal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppCalRepo extends JpaRepository<AppCal, Long> {
}
