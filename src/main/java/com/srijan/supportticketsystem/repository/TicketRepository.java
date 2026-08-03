package com.srijan.supportticketsystem.repository;

import com.srijan.supportticketsystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}