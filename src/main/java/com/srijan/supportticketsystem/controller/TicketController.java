package com.srijan.supportticketsystem.controller;

import com.srijan.supportticketsystem.dto.TicketRequest;
import com.srijan.supportticketsystem.dto.TicketResponse;
import com.srijan.supportticketsystem.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public TicketResponse getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }
    @PutMapping("/{id}")
    public TicketResponse updateTicket(@PathVariable Long id,
                                       @Valid @RequestBody TicketRequest request) {

        return ticketService.updateTicket(id, request);
    }
    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable Long id) {

        ticketService.deleteTicket(id);

        return "Ticket deleted successfully";
    }
}