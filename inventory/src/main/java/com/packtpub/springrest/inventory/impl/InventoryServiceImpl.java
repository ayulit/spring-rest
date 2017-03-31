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

/**
 * {@link InventoryService} implementation.
 *
 * @author Ludovic Dewailly
 */
@Component
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);
    
    @PersistenceContext // for injection: JPA classics
    private EntityManager entityManager;

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
