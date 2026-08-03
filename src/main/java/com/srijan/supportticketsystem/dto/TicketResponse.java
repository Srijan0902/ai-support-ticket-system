package com.srijan.supportticketsystem.dto;

import com.srijan.supportticketsystem.enums.Category;
import com.srijan.supportticketsystem.enums.Priority;
import com.srijan.supportticketsystem.enums.TicketStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponse {

    private Long id;
    private String description;
    private Category category;
    private Priority priority;
    private TicketStatus status;
    private LocalDateTime createdAt;
    private Long customerId;
}