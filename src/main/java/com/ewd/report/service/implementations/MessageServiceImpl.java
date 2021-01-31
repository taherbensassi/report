package com.ewd.report.service.implementations;


import com.ewd.report.entity.Claim;
import com.ewd.report.exception.ResourceNotFoundException;
import com.ewd.report.repository.ClaimRepository;
import com.ewd.report.service.Interfaces.ClaimService;
import com.ewd.report.service.Interfaces.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private ClaimRepository claimRepository;

    public MessageServiceImpl(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }


}
