package com.srijan.supportticketsystem.service.impl;

import com.srijan.supportticketsystem.ai.GeminiService;
import com.srijan.supportticketsystem.dto.TicketRequest;
import com.srijan.supportticketsystem.dto.TicketResponse;
import com.srijan.supportticketsystem.entity.Customer;
import com.srijan.supportticketsystem.entity.Ticket;
import com.srijan.supportticketsystem.enums.Category;
import com.srijan.supportticketsystem.enums.Priority;
import com.srijan.supportticketsystem.enums.TicketStatus;
import com.srijan.supportticketsystem.exception.CustomerNotFoundException;
import com.srijan.supportticketsystem.exception.TicketNotFoundException;
import com.srijan.supportticketsystem.repository.CustomerRepository;
import com.srijan.supportticketsystem.repository.TicketRepository;
import com.srijan.supportticketsystem.service.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final GeminiService geminiService;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             CustomerRepository customerRepository,
                             GeminiService geminiService) {
        this.ticketRepository = ticketRepository;
        this.customerRepository = customerRepository;
        this.geminiService = geminiService;
    }

    @Override
    public TicketResponse createTicket(TicketRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        String aiResponse;

        try {
            aiResponse = geminiService.classifyTicket(request.getDescription());
        } catch (Exception e) {

            aiResponse = """
                    Category:GENERAL
                    Priority:MEDIUM
                    Summary:Unable to classify ticket.
                    """;

            System.out.println("Gemini unavailable. Using default values.");
        }

        System.out.println("========== AI RESPONSE ==========");
        System.out.println(aiResponse);
        System.out.println("=================================");

        Category category = Category.GENERAL;
        Priority priority = Priority.MEDIUM;
        String summary = "";

        try {

            String[] lines = aiResponse.split("\\r?\\n");

            for (String line : lines) {

                if (line.startsWith("Category:")) {
                    category = Category.valueOf(
                            line.replace("Category:", "")
                                    .trim()
                                    .toUpperCase()
                    );
                }

                if (line.startsWith("Priority:")) {
                    priority = Priority.valueOf(
                            line.replace("Priority:", "")
                                    .trim()
                                    .toUpperCase()
                    );
                }

                if (line.startsWith("Summary:")) {
                    summary = line.replace("Summary:", "").trim();
                }
            }

        } catch (Exception e) {
            System.out.println("Failed to parse AI response.");
            System.out.println("Using default values.");
        }

        Ticket ticket = Ticket.builder()
                .description(request.getDescription())
                .category(category)
                .priority(priority)
                .summary(summary)
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
                .summary(savedTicket.getSummary())
                .status(savedTicket.getStatus())
                .createdAt(savedTicket.getCreatedAt())
                .customerId(savedTicket.getCustomer().getId())
                .build();
    }

    @Override
    public Page<TicketResponse> getAllTickets(Category category,
                                              Priority priority,
                                              Pageable pageable) {

        Page<Ticket> tickets;

        if (category != null && priority != null) {

            tickets = ticketRepository.findByCategoryAndPriority(
                    category,
                    priority,
                    pageable
            );

        } else if (category != null) {

            tickets = ticketRepository.findByCategory(
                    category,
                    pageable
            );

        } else if (priority != null) {

            tickets = ticketRepository.findByPriority(
                    priority,
                    pageable
            );

        } else {

            tickets = ticketRepository.findAll(pageable);

        }

        return tickets.map(ticket -> TicketResponse.builder()
                .id(ticket.getId())
                .description(ticket.getDescription())
                .category(ticket.getCategory())
                .priority(ticket.getPriority())
                .summary(ticket.getSummary())
                .status(ticket.getStatus())
                .createdAt(ticket.getCreatedAt())
                .customerId(ticket.getCustomer().getId())
                .build());
    }

    @Override
    public TicketResponse getTicketById(Long id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        return TicketResponse.builder()
                .id(ticket.getId())
                .description(ticket.getDescription())
                .category(ticket.getCategory())
                .priority(ticket.getPriority())
                .summary(ticket.getSummary())
                .status(ticket.getStatus())
                .createdAt(ticket.getCreatedAt())
                .customerId(ticket.getCustomer().getId())
                .build();
    }

    @Override
    public TicketResponse updateTicket(Long id, TicketRequest request) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        ticket.setDescription(request.getDescription());
        ticket.setCustomer(customer);

        Ticket updatedTicket = ticketRepository.save(ticket);

        return TicketResponse.builder()
                .id(updatedTicket.getId())
                .description(updatedTicket.getDescription())
                .category(updatedTicket.getCategory())
                .priority(updatedTicket.getPriority())
                .summary(updatedTicket.getSummary())
                .status(updatedTicket.getStatus())
                .createdAt(updatedTicket.getCreatedAt())
                .customerId(updatedTicket.getCustomer().getId())
                .build();
    }

    @Override
    public void deleteTicket(Long id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        ticketRepository.delete(ticket);
    }
}