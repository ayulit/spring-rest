package com.packtpub.springrest.inventory;

import com.packtpub.springrest.model.Room;

public interface InventoryService {
	
	/* look up a room by its identifier */
	public Room getRoom(long roomId);
	
	// TODO define other methods

}
