package com.ewd.report.controller;


import com.ewd.report.entity.Claim;
import com.ewd.report.service.Interfaces.ClaimService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClaimController {
    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping("/claims")
    public List<Claim> getAllClaims(){
        return claimService.getAllClaims();
    }

    @PostMapping("/claim")
    public Claim addClaim(@RequestBody Claim claim){
        return claimService.addClaim(claim);
    }

    @GetMapping("/claim/{id}")
    public Claim getClaimById(@PathVariable(value = "id") Long claimId) {
        return claimService.getClaimById(claimId);
    }

    @PutMapping("/claim/{id}")
    public boolean updateClaim(@Valid @RequestBody Claim claim, @PathVariable("id") Long id){
        return claimService.updateClaim(claim, id);
    }

    @DeleteMapping("/claim/{id}")
    public void deleteClaim(@PathVariable("id") Long id){
        claimService.deleteClaim(id);
    }


    @PutMapping("/claim/confirm/{id}")
    public boolean confirmClaim(@Valid @RequestBody Claim claim, @PathVariable("id") Long id){
        return claimService.confirmClaim(claim, id);
    }

    @PutMapping("/claim/assigned-moderator/{id}")
    public boolean assignedClaimToModerator(@Valid @RequestBody Claim claim, @PathVariable("id") Long id){
        return claimService.assignedClaimToModerator(claim, id);
    }

}
