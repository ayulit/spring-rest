package com.packtpub.springrest.inventory.web;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.packtpub.springrest.model.Room;

/* Data Transfer Object (DTO) pattern:
   It provides a useful decoupling between the persistence and presentation layers.*/
public class RoomDTO {

	private long id;
    private String name;
    private long roomCategoryId;
    private String description;
    
    public RoomDTO() { }
    
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
	
    @Override
    public String toString() {
        // some kind of building string from reflection of class name ...?
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
