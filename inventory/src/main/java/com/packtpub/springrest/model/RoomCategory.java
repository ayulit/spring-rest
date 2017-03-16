package com.packtpub.springrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="room_categories")
public class RoomCategory {

    private long id;
    private String name;
    private String description;

    // TODO implement Pricing with getters and setters
    
    @Id
    @GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="name", unique=true, nullable=false, length=128)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="description", length=2048)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    

	// TODO override toString
}
