package com.ewd.report.controller;


import com.ewd.report.entity.Claim;
import com.ewd.report.service.Interfaces.ModeratorService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ModeratorController {

    private final ModeratorService moderatorService;

    public ModeratorController(ModeratorService moderatorService) {
        this.moderatorService = moderatorService;
    }

    @PutMapping("/moderator/claim/confirm/{id}")
    public boolean approveClaim(@Valid @RequestBody Claim claim, @PathVariable("id") Long id){
        return moderatorService.approveClaim(claim, id);
    }

    @PutMapping("/moderator/claim/reject/{id}")
    public boolean rejectClaim(@Valid @RequestBody Claim claim, @PathVariable("id") Long id){
        return moderatorService.rejectClaim(claim, id);
    }

}
