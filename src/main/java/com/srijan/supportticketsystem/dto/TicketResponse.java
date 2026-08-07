package com.srijan.supportticketsystem.dto;

import com.srijan.supportticketsystem.enums.Category;
import com.srijan.supportticketsystem.enums.Priority;
import com.srijan.supportticketsystem.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {

    private Long id;

    private String description;

    private Category category;

    private Priority priority;

    // AI Generated Summary
    private String summary;

    private TicketStatus status;

    private LocalDateTime createdAt;

    private Long customerId;
}