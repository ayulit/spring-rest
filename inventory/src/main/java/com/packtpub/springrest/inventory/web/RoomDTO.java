package com.packtpub.springrest.inventory.web;

import com.packtpub.springrest.model.Room;

/* Data Transfer Object (DTO) pattern:
   It provides a useful decoupling between the persistence and presentation layers.*/
public class RoomDTO {

	private long id;
    private String name;
    private long roomCategoryId;
    private String description;
    
    // constructor with Room parameter
    public RoomDTO(Room room) {	
		this.id = room.getId();
		this.name = room.getName();
		this.roomCategoryId = room.getRoomCategory().getId();
		this.description = room.getDescription();
	}
        
    /* getters ONLY */
    
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public long getRoomCategoryId() {
		return roomCategoryId;
	}
	public String getDescription() {
		return description;
	}


}
