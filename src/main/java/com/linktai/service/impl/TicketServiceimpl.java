package com.linktai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linktai.dao.TicketMapper;
import com.linktai.pojo.Ticket;
import com.linktai.service.ITicketService;
@Service
public class TicketServiceimpl implements ITicketService{
	@Autowired
	private TicketMapper ticketMapper;
	
	public Integer findCount() {
		Ticket ticket = ticketMapper.findTicket();
		return ticket.getNumber();
	}

}
