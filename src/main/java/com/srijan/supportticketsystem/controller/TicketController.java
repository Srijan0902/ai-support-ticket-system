package com.srijan.supportticketsystem.controller;

import com.srijan.supportticketsystem.dto.TicketRequest;
import com.srijan.supportticketsystem.dto.TicketResponse;
import com.srijan.supportticketsystem.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public TicketResponse createTicket(@Valid @RequestBody TicketRequest request) {
        return ticketService.createTicket(request);
    }
}