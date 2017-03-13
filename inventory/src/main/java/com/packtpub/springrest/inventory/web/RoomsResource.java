package com.packtpub.springrest.inventory.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.packtpub.springrest.inventory.InventoryService;
import com.packtpub.springrest.model.Room;

@RestController
@RequestMapping("/rooms")     // like @Path in JAX-RS
public class RoomsResource {  // resource like in JAX-RS
	
	// TODO implement injection using @Autowired
	private final InventoryService inventoryService;
	
	public RoomsResource(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
	
	@RequestMapping(value="/{roomId}", method = RequestMethod.GET)  // like @GET in JAX-RS
	public RoomDTO getRoom(@PathVariable("roomId") long id) {
		Room room = inventoryService.getRoom(id);
		return new RoomDTO(room);
	}
	
	// TODO implement other API methods

}
