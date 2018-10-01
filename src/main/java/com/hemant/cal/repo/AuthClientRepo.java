package com.hemant.cal.repo;

import com.hemant.cal.model.AuthClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthClientRepo extends JpaRepository<AuthClient, String> {
}
