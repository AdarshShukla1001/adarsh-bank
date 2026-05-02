package com.bank_system.adarsh_bank.transfer;

import com.bank_system.adarsh_bank.transfer.dto.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/internal")
    public String transferInternal(@RequestBody TransferRequest request) {
        transferService.transferInternal(
            request.getFromAccountId(),
            request.getToAccountId(),
            request.getAmount()
        );
        return "Transfer successful";
    }
}
