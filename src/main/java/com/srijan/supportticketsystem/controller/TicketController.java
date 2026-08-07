package com.srijan.supportticketsystem.controller;

import com.srijan.supportticketsystem.dto.TicketRequest;
import com.srijan.supportticketsystem.dto.TicketResponse;
import com.srijan.supportticketsystem.enums.Category;
import com.srijan.supportticketsystem.enums.Priority;
import com.srijan.supportticketsystem.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Ticket API", description = "Operations related to Support Tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @Operation(summary = "Create a new support ticket")
    public TicketResponse createTicket(@Valid @RequestBody TicketRequest request) {
        return ticketService.createTicket(request);
    }

    @GetMapping
    @Operation(summary = "Get all tickets with pagination and filtering")
    public Page<TicketResponse> getAllTickets(

            @RequestParam(required = false) Category category,

            @RequestParam(required = false) Priority priority,

            Pageable pageable) {

        return ticketService.getAllTickets(category, priority, pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get ticket by ID")
    public TicketResponse getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update ticket")
    public TicketResponse updateTicket(@PathVariable Long id,
                                       @Valid @RequestBody TicketRequest request) {

        return ticketService.updateTicket(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete ticket")
    public String deleteTicket(@PathVariable Long id) {

        ticketService.deleteTicket(id);

        return "Ticket deleted successfully";
    }
}