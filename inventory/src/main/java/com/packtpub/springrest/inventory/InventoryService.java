package com.packtpub.springrest.inventory;

import java.util.List;

import com.packtpub.springrest.model.Room;
import com.packtpub.springrest.model.RoomCategory;

public interface InventoryService {

    public void addRoomCategory(RoomCategory category);

    public RoomCategory getRoomCategory(long categoryId);

    public void addRoom(Room room);

    /* look up a room by its identifier */
    public Room getRoom(long roomId);

    public List<Room> getAllRoomsWithCategory(RoomCategory category);

}
