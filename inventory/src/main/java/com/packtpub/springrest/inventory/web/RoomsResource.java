package com.packtpub.springrest.inventory.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.packtpub.springrest.inventory.InventoryService;
import com.packtpub.springrest.model.Room;
import com.packtpub.springrest.model.RoomCategory;

@RestController               // we instruct Spring that RoomsResource is a controller = @Component? It will be bean? 
@RequestMapping("/rooms")     // like @Path in JAX-RS
public class RoomsResource {  // resource like in JAX-RS : maps request whithin the code

    @Autowired
    private InventoryService inventoryService;
    
    public RoomsResource() {}

    // retrieving a Room by identifier
    @RequestMapping(value="/{roomId}", method = RequestMethod.GET)  // like @GET in JAX-RS
    public RoomDTO getRoom(@PathVariable("roomId") long id) {
        Room room = inventoryService.getRoom(id);

        // Data Transfer Object (DTO) pattern:
        // It provides a useful decoupling between the persistence and presentation layers.
        return new RoomDTO(room);
    }

    @RequestMapping(method=RequestMethod.GET)
    public List<RoomDTO> getRoomsInCategory(@RequestParam("categoryId") long categoryId) {
        RoomCategory category = inventoryService.getRoomCategory(categoryId);

        return inventoryService.getAllRoomsWithCategory(category)
                .stream()                      // Java-8 Stream API
                .map(RoomDTO::new)             // Java-8 Method Reference
                .collect(Collectors.toList());
    } 


}
