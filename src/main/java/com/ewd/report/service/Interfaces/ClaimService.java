package com.ewd.report.service.Interfaces;

import com.ewd.report.entity.Claim;

import java.util.List;

public interface ClaimService {

    List<Claim> getAllClaims();

    Claim addClaim(Claim claim);

    Claim getClaimById(Long id);

    Boolean updateClaim(Claim claim, Long id);

    void deleteClaim(Long id);
}
