package com.packtpub.springrest.inventory.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.packtpub.springrest.RecordNotFoundException;
import com.packtpub.springrest.inventory.InventoryService;
import com.packtpub.springrest.model.Pricing;
import com.packtpub.springrest.model.PricingModel;
import com.packtpub.springrest.model.Room;
import com.packtpub.springrest.model.RoomCategory;


@Component
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);
    
    @PersistenceContext // for injection: JPA classics
    private EntityManager entityManager;

    public InventoryServiceImpl() { }
    
    @Override
    public void addRoomCategory(RoomCategory category) {
        if (category.getId() > 0) {
            throw new IllegalArgumentException("category already exists");
        }
        checkPricing(category);
        entityManager.persist(category.getPricing());
        entityManager.persist(category);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Added new room category {}", category);
        }
    }

    // Not used yet.
    @Override
    public void updateRoomCategory(RoomCategory category) {
        if (category.getId() == 0) {
            throw new IllegalArgumentException("room category does not exist");
        }
        checkPricing(category);
        entityManager.merge(category.getPricing());
        entityManager.merge(category);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Updated room category {}", category);
        }
    }
    
    /**
     * Validates that the pricing object is valid.
     *
     * @param category the category to check the pricing for
     */
    private void checkPricing(RoomCategory category) {
        Pricing pricing = category.getPricing();
        if (pricing == null) {
            throw new IllegalArgumentException("category must have a pricing");
        }
        if (pricing.getPriceGuest1() == null) {
            throw new IllegalArgumentException("Pricing requires a guest 1 price");
        }
        if (pricing.getPricingModel() == PricingModel.SLIDING && pricing.getPriceGuest2() == null) {
            throw new IllegalArgumentException("Sliding pricing requires a guest 2 price");
        }
    }

    @Override
    public RoomCategory getRoomCategory(long categoryId) {
        if (categoryId <= 0) {
            throw new IllegalArgumentException("Invalid category ID. It must be greater than zero");
        }
        RoomCategory category = entityManager.find(RoomCategory.class, categoryId);
        if (category == null) {
            throw new RecordNotFoundException("No room category with ID " + categoryId);
        }
        return category;
    }

    // Not used yet.
    @Override
    public void deleteRoomCategory(long categoryId) {
        if (categoryId <= 0) {
            throw new IllegalArgumentException("Invalid category ID. It must be greater than zero");
        }
        RoomCategory category = getRoomCategory(categoryId);
        entityManager.remove(category);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Deleted room category {}", category);
        }
    }

    // XXX Warning! Criteria API
    // Not used yet.
    @Override
    public List<RoomCategory> getAllRoomCategories() {
        CriteriaBuilder  cb = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<RoomCategory> q = cb.createQuery(RoomCategory.class);
        Root<RoomCategory> rootEntry = q.from(RoomCategory.class);
        
        CriteriaQuery<RoomCategory> all = q.select(rootEntry);
        TypedQuery<RoomCategory> allQuery = entityManager.createQuery(all);
        
        return allQuery.getResultList();

    }
    
    @Override
    public void addRoom(Room room) {
        if (room.getId() > 0) {
            throw new IllegalArgumentException("room already exists");
        }
        entityManager.merge(room);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Added new room {}", room);
        }
    }

    @Transactional(readOnly = true)
    public Room getRoom(long roomId) {
        if (roomId <= 0) {
            throw new IllegalArgumentException("Invalid room ID. It must be greater than zero");
        }
        Room room = entityManager.find(Room.class, roomId);
        if (room == null) {
            throw new RecordNotFoundException("No room with ID " + roomId);
        }
        return room;
    }
    
    public void updateRoom(Room room) {
        if (room.getId() == 0) {
            throw new IllegalArgumentException("room does not exist");
        }
        entityManager.merge(room);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Updated room {}", room);
        }
    }
    
    public void deleteRoom(long roomId) {
        if (roomId <= 0) {
            throw new IllegalArgumentException("Invalid room ID. It must be greater than zero");
        }
        Room room = getRoom(roomId);
        entityManager.remove(room);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Deleted room {}", room);
        }
    }    

    // XXX Warning! Criteria API
    // Not used yet.
    @Transactional(readOnly = true)
    public List<Room> getAllRooms() {
        CriteriaBuilder  cb = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Room> q = cb.createQuery(Room.class);
        Root<Room> rootEntry = q.from(Room.class);
        
        CriteriaQuery<Room> all = q.select(rootEntry);
        TypedQuery<Room> allQuery = entityManager.createQuery(all);
        
        return allQuery.getResultList();
    }
    
    // XXX Warning! Criteria API
    @Transactional(readOnly = true)
    public List<Room> getAllRoomsWithCategory(RoomCategory category) {
        
        CriteriaBuilder  criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
        Root<Room> rootEntry = criteriaQuery.from(Room.class);
        CriteriaQuery<Room> all = criteriaQuery.select(rootEntry);

        TypedQuery<Room> allQuery = entityManager.createQuery(all);

        // TODO add filtering by 'category'
        return allQuery.getResultList();
    }
}
