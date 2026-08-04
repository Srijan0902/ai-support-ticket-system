package com.srijan.supportticketsystem.service.impl;

import com.srijan.supportticketsystem.dto.TicketRequest;
import com.srijan.supportticketsystem.dto.TicketResponse;
import com.srijan.supportticketsystem.entity.Customer;
import com.srijan.supportticketsystem.entity.Ticket;
import com.srijan.supportticketsystem.enums.Category;
import com.srijan.supportticketsystem.enums.Priority;
import com.srijan.supportticketsystem.enums.TicketStatus;
import com.srijan.supportticketsystem.repository.CustomerRepository;
import com.srijan.supportticketsystem.repository.TicketRepository;
import com.srijan.supportticketsystem.service.TicketService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             CustomerRepository customerRepository) {
        this.ticketRepository = ticketRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public TicketResponse createTicket(TicketRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Ticket ticket = Ticket.builder()
                .description(request.getDescription())
                .category(Category.GENERAL)
                .priority(Priority.MEDIUM)
                .status(TicketStatus.OPEN)
                .createdAt(LocalDateTime.now())
                .customer(customer)
                .build();

        Ticket savedTicket = ticketRepository.save(ticket);

        return TicketResponse.builder()
                .id(savedTicket.getId())
                .description(savedTicket.getDescription())
                .category(savedTicket.getCategory())
                .priority(savedTicket.getPriority())
                .status(savedTicket.getStatus())
                .createdAt(savedTicket.getCreatedAt())
                .customerId(savedTicket.getCustomer().getId())
                .build();
    }

    @Override
    public List<TicketResponse> getAllTickets() {

        List<Ticket> tickets = ticketRepository.findAll();

        return tickets.stream()
                .map(ticket -> TicketResponse.builder()
                        .id(ticket.getId())
                        .description(ticket.getDescription())
                        .category(ticket.getCategory())
                        .priority(ticket.getPriority())
                        .status(ticket.getStatus())
                        .createdAt(ticket.getCreatedAt())
                        .customerId(ticket.getCustomer().getId())
                        .build())
                .toList();
    }

    @Override
    public TicketResponse getTicketById(Long id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        return TicketResponse.builder()
                .id(ticket.getId())
                .description(ticket.getDescription())
                .category(ticket.getCategory())
                .priority(ticket.getPriority())
                .status(ticket.getStatus())
                .createdAt(ticket.getCreatedAt())
                .customerId(ticket.getCustomer().getId())
                .build();
    }
    @Override
    public TicketResponse updateTicket(Long id, TicketRequest request) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        ticket.setDescription(request.getDescription());
        ticket.setCustomer(customer);

        Ticket updatedTicket = ticketRepository.save(ticket);

        return TicketResponse.builder()
                .id(updatedTicket.getId())
                .description(updatedTicket.getDescription())
                .category(updatedTicket.getCategory())
                .priority(updatedTicket.getPriority())
                .status(updatedTicket.getStatus())
                .createdAt(updatedTicket.getCreatedAt())
                .customerId(updatedTicket.getCustomer().getId())
                .build();
    }
    @Override
    public void deleteTicket(Long id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticketRepository.delete(ticket);
    }
}