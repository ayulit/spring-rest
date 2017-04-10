package com.packtpub.springrest.booking.web;

import com.packtpub.springrest.booking.BookingService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Resource (controller) class for {@link com.packtpub.springrest.model.Booking}s.
 *
 * @author Ludovic Dewailly
 */
@RestController
@RequestMapping("/bookings")
public class BookingsResource {

    private static final Logger logger = LoggerFactory.getLogger(BookingsResource.class);
    
    @Autowired
    private BookingService bookingService;

    public BookingsResource() {}
    
    @RequestMapping(value = "/{bookingId}", method = RequestMethod.GET)
    public BookingDTO getBooking(@PathVariable("bookingId") long bookingId) {
        return new BookingDTO(bookingService.getBooking(bookingId));
    }    
    
}
