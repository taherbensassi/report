package com.ewd.report.service.implementations;


import com.ewd.report.entity.Category;
import com.ewd.report.entity.Claim;
import com.ewd.report.exception.ResourceNotFoundException;
import com.ewd.report.repository.ClaimRepository;
import com.ewd.report.service.Interfaces.ClaimService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService {

    private ClaimRepository claimRepository;

    public ClaimServiceImpl(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    @Override
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    @Override
    public Claim addClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    @Override
    public Claim getClaimById(Long id) {
        return claimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Claim", "id", id));
    }

    @Override
    public Boolean updateClaim(Claim claim, Long id) {
        Claim claimData = claimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        claimData.setFoundItem(claim.getFoundItem());
        claimData.setUser(claim.getUser());
        claimData.setAssigned(claim.getAssigned());
        claimData.setMessage(claim.getMessage());
        claimData.setReturned(claim.getReturned());
        claimData.setStatus(claim.getStatus());
        claimRepository.save(claimData);
        return true;
    }

    @Override
    public void deleteClaim(Long id) {
        Claim claim = claimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Claim", "id", id));
        claimRepository.delete(claim);
        ResponseEntity.ok().build();
    }

    @Override
    public Boolean confirmClaim(Claim claim, Long id) {
        Claim claimData = claimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Claim", "id", id));
        // 0 - init
        // 1 - confirmed claimed
        // 2 - Assigned to moderator
        claimData.setStatus(1);
        claimRepository.save(claimData);
        return true;
    }

    @Override
    public Boolean assignedClaimToModerator(Claim claim, Long id) {
        Claim claimData = claimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Claim", "id", id));
        // 0 - first time
        // 1 - confirmed claimed
        // 2 - Assigned to moderator
        claimData.setStatus(2);
        claimRepository.save(claimData);
        return true;
    }
}
