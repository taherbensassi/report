package com.ewd.report.service.Interfaces;

import com.ewd.report.entity.Claim;
import com.ewd.report.entity.FoundItem;

import java.util.List;

public interface ModeratorService {


    Boolean approveClaim(Claim claim, Long id);
    Boolean rejectClaim(Claim claim, Long id);


}
