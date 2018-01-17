package com.linktai.dao;

import com.linktai.pojo.Ticket;

public interface TicketMapper {
    int insert(Ticket record);

	int insertSelective(Ticket record);
	
	Ticket findTicket();

}