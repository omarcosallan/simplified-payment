package com.marcos.simplified_payment.dto.transfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransferDTO(BigDecimal value, UUID payer, UUID receiver, LocalDateTime transferDate) {
}
