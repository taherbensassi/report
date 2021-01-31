package com.ewd.report.service.implementations;

import com.ewd.report.entity.Claim;
import com.ewd.report.exception.ResourceNotFoundException;
import com.ewd.report.repository.ClaimRepository;
import com.ewd.report.service.Interfaces.ModeratorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeratorServiceImpl implements ModeratorService {

    private ClaimRepository claimRepository;

    public ModeratorServiceImpl(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    @Override
    public Boolean approveClaim(Claim claim, Long id) {
        Claim claimData = claimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Claim", "id", id));
        claimData.setStatus(1);
        claimRepository.save(claimData);
        return true;
    }

    @Override
    public Boolean rejectClaim(Claim claim, Long id) {
        Claim claimData = claimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Claim", "id", id));
        claimData.setStatus(0);
        claimRepository.save(claimData);
        return true;
    }
}
