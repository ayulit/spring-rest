package com.packtpub.springrest.inventory.web;

import java.util.List;

import com.packtpub.springrest.RecordNotFoundException;

import com.packtpub.springrest.inventory.InventoryService;
import com.packtpub.springrest.inventory.web.ApiResponse.ApiError;
import com.packtpub.springrest.inventory.web.ApiResponse.Status;
import com.packtpub.springrest.model.Room;
import com.packtpub.springrest.model.RoomCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;



@RestController  
@RequestMapping("/rooms") 
public class RoomsResource { 

    @Autowired
    private InventoryService inventoryService;
    
    public RoomsResource() {}


    @RequestMapping(value="/{roomId}", method = RequestMethod.GET)
    public ApiResponse getRoom(@PathVariable("roomId") long id) {
        try {
            Room room = inventoryService.getRoom(id);
            return new ApiResponse(Status.OK, new RoomDTO(room), null);
        } catch (RecordNotFoundException e) {
            return new ApiResponse(Status.ERROR, null, new ApiError(999, "No room with ID " + id));
        }
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
