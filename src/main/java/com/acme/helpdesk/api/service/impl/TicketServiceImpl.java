package com.acme.helpdesk.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.acme.helpdesk.api.entity.ChangeStatus;
import com.acme.helpdesk.api.entity.Ticket;
import com.acme.helpdesk.api.repository.ChangeStatusRepository;
import com.acme.helpdesk.api.repository.TicketRepository;
import com.acme.helpdesk.api.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private ChangeStatusRepository changeStatusRepository;

	@Override
	public Ticket createOrUpdate(Ticket ticket) {

		return this.ticketRepository.save(ticket);
	}

	@Override
	public Ticket findById(String id) {

		return this.ticketRepository.findOne(id);
	}

	@Override
	public void delete(String id) {
		this.ticketRepository.delete(id);

	}

	@Override
	public Page<Ticket> listTicket(int page, int count) {
		Pageable pages = new PageRequest(page, count);

		return this.ticketRepository.findAll(pages);
	}

	@Override
	public ChangeStatus createChangeStatus(ChangeStatus changeStatus) {

		return this.changeStatusRepository.save(changeStatus);
	}

	@Override
	public Iterable<ChangeStatus> listChangeStatus(String ticketId) {

		return this.changeStatusRepository.findByTicketIdOrderByDateChangeStatusDesc(ticketId);
	}

	@Override
	public Page<Ticket> findByCurrentUser(int page, int count, String userId) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByUserIdOrderByDateDesc(pages, userId);
	}

	@Override
	public Page<Ticket> findByParameters(int page, int count, String title, String status, String priority) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByTitleIgnoreCaseContainingAndStatusAndPriorityOrderByDateDesc(title, status,
				priority, pages);
	}

	@Override
	public Page<Ticket> findByParametersAndCurrentUser(int page, int count, String title, String status,
			String priority, String userId) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByTitleIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByDateDesc(title,
				status, priority, pages);

	}

	@Override
	public Page<Ticket> findByNumber(int page, int count, int number) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByNumber(pages, number);
	}

	@Override
	public Iterable<Ticket> findAll() {

		return this.ticketRepository.findAll();
	}

	@Override
	public Page<Ticket> findByParameterAndAssignedUser(int page, int count, String title, String status,
			String priority, String assignedUserId) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository
				.findByTitleIgnoreCaseContainingAndStatusAndPriorityAndAssignedUserIdOrderByDateDesc(title, status,
						priority, pages);
	}

}
