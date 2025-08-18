package com.interior.archiThink.repository;

import com.interior.archiThink.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {}
