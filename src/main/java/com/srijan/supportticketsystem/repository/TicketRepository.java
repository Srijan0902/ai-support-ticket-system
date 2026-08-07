package com.srijan.supportticketsystem.repository;

import com.srijan.supportticketsystem.entity.Ticket;
import com.srijan.supportticketsystem.enums.Category;
import com.srijan.supportticketsystem.enums.Priority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Page<Ticket> findByCategory(Category category, Pageable pageable);

    Page<Ticket> findByPriority(Priority priority, Pageable pageable);

    Page<Ticket> findByCategoryAndPriority(Category category,
                                           Priority priority,
                                           Pageable pageable);
}