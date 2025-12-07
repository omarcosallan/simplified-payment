package com.marcos.simplified_payment.service;

import com.marcos.simplified_payment.dto.transfer.TransferDTO;
import com.marcos.simplified_payment.dto.transfer.TransferRequestDTO;
import com.marcos.simplified_payment.entity.Transfer;
import com.marcos.simplified_payment.entity.User;
import com.marcos.simplified_payment.entity.enums.UserType;
import com.marcos.simplified_payment.exception.BusinessException;
import com.marcos.simplified_payment.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;
    private final UserService userService;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;

    @Transactional
    public TransferDTO transfer(TransferRequestDTO dto) {
        User payer = userService.findById(dto.payer());
        User receiver = userService.findById(dto.receiver());

        if (payer.getUserType().equals(UserType.MERCHANT)) {
            throw new BusinessException("The user cannot make a transfer");
        }

        if (payer.getWallet().getBalance().compareTo(dto.value()) < 0) {
            throw new BusinessException("Insufficient funds");
        }

        Transfer transfer = new Transfer();
        transfer.setBalance(dto.value());
        transfer.setPayer(payer);
        transfer.setReceiver(receiver);

        payer.getWallet().setBalance(payer.getWallet().getBalance().subtract(dto.value()));
        receiver.getWallet().setBalance(receiver.getWallet().getBalance().add(dto.value()));

        if (!authorizationService.authorize(transfer)) {
            throw new BusinessException("Transfer unavailable");
        }

        userService.save(payer);
        userService.save(receiver);
        transferRepository.save(transfer);

        notificationService.send(receiver.getEmail(), "Transaction received");

        return new TransferDTO(transfer.getBalance(),
                transfer.getPayer().getId(),
                transfer.getReceiver().getId(),
                transfer.getTransferDate());
    }
}
