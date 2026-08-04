package com.srijan.supportticketsystem.service;

import com.srijan.supportticketsystem.dto.TicketRequest;
import com.srijan.supportticketsystem.dto.TicketResponse;

import java.util.List;

public interface TicketService {
    void deleteTicket(Long id);

    // Create Ticket
    TicketResponse createTicket(TicketRequest request);

    // Get All Tickets
    List<TicketResponse> getAllTickets();

    // Get Ticket By Id
    TicketResponse getTicketById(Long id);
    TicketResponse updateTicket(Long id, TicketRequest request);
}