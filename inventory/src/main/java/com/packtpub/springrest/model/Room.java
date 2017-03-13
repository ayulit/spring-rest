package com.packtpub.springrest.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="rooms")
public class Room {

	private long id;
	private RoomCategory roomCategory;
	private String name;
	private String description;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	/* Many Rooms to One RoomCategory */
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	public RoomCategory getRoomCategory() {
		return roomCategory;
	}
	public void setRoomCategory(RoomCategory roomCategory) {
		this.roomCategory = roomCategory;
	}
	
	@Column(name="name", unique=true, nullable=false, length=128)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
