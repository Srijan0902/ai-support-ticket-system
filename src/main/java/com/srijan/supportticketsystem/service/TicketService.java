package com.srijan.supportticketsystem.service;

import com.srijan.supportticketsystem.dto.TicketRequest;
import com.srijan.supportticketsystem.dto.TicketResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.srijan.supportticketsystem.enums.Category;
import com.srijan.supportticketsystem.enums.Priority;
public interface TicketService {

    // Create Ticket
    TicketResponse createTicket(TicketRequest request);

    // Get All Tickets with Pagination
    Page<TicketResponse> getAllTickets(Category category,
                                       Priority priority,
                                       Pageable pageable);
    // Get Ticket By Id
    TicketResponse getTicketById(Long id);

    // Update Ticket
    TicketResponse updateTicket(Long id, TicketRequest request);

    // Delete Ticket
    void deleteTicket(Long id);
}