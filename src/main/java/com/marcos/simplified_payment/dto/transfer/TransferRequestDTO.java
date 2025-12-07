package com.marcos.simplified_payment.dto.transfer;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferRequestDTO(BigDecimal value, UUID payer, UUID receiver) {
}
