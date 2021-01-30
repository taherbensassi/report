package com.ewd.report.repository;

import com.ewd.report.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

    Optional<Claim> findById(Long id);


}
