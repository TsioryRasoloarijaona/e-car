package com.web3.ecommerceback.repository;

import com.web3.ecommerceback.entities.Appointement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointementRepository extends JpaRepository<Appointement , Long> {

}
