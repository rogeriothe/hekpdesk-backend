package com.acme.helpdesk.api.repository;

import java.util.Iterator;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.acme.helpdesk.api.entity.ChangeStatus;

public interface ChangeStatusRepository extends MongoRepository<ChangeStatus, String> {
	
	Iterator<ChangeStatus> findByTicketIdOrderByDateChangeStatusDesc(String ticketId);

}
