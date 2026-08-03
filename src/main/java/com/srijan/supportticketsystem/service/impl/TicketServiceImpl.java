package com.srijan.supportticketsystem.service.impl;

import com.srijan.supportticketsystem.dto.TicketRequest;
import com.srijan.supportticketsystem.dto.TicketResponse;
import com.srijan.supportticketsystem.repository.CustomerRepository;
import com.srijan.supportticketsystem.repository.TicketRepository;
import com.srijan.supportticketsystem.service.TicketService;
import org.springframework.stereotype.Service;
import com.srijan.supportticketsystem.entity.Customer;
import com.srijan.supportticketsystem.entity.Ticket;
import com.srijan.supportticketsystem.enums.Category;
import com.srijan.supportticketsystem.enums.Priority;
import com.srijan.supportticketsystem.enums.TicketStatus;

import java.time.LocalDateTime;
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
                .customer(customer)
                .category(Category.GENERAL)
                .priority(Priority.MEDIUM)
                .status(TicketStatus.OPEN)
                .createdAt(LocalDateTime.now())
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
}