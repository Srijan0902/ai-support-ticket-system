package com.srijan.supportticketsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketRequest {

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Customer Id is required")
    private Long customerId;
}