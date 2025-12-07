package com.marcos.simplified_payment.controller;

import com.marcos.simplified_payment.dto.transfer.TransferDTO;
import com.marcos.simplified_payment.dto.transfer.TransferRequestDTO;
import com.marcos.simplified_payment.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<TransferDTO> transfer(@RequestBody TransferRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.transfer(dto));
    }
}
