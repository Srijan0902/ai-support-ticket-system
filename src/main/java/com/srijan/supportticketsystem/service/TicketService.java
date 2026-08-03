package com.srijan.supportticketsystem.service;

import com.srijan.supportticketsystem.dto.TicketRequest;
import com.srijan.supportticketsystem.dto.TicketResponse;

public interface TicketService {

    TicketResponse createTicket(TicketRequest request);

}