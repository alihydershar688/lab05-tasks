package com.scad.lab5_task1.api;

import com.scad.lab5_task1.data.ServiceBooking;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/bookings")
public class ServiceBookingController {

    private final List<ServiceBooking> bookings = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @PostMapping("/register")
    public ServiceBooking registerBooking(@RequestBody ServiceBooking booking) {
        booking.setId(String.valueOf(counter.incrementAndGet()));
        bookings.add(booking);
        return booking;
    }

    @GetMapping
    public List<ServiceBooking> getAllBookings() {
        return bookings;
    }

    @GetMapping("/{id}")
    public ServiceBooking getBookingById(@PathVariable String id) {
        return bookings.stream()
                .filter(booking -> booking.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    public ServiceBooking updateBooking(@PathVariable String id, @RequestBody ServiceBooking updatedBooking) {
        Optional<ServiceBooking> existingBooking = bookings.stream()
                .filter(booking -> booking.getId().equals(id))
                .findFirst();

        existingBooking.ifPresent(booking -> {
            booking.setName(updatedBooking.getName());
            booking.setModel(updatedBooking.getModel());
            booking.setRegNum(updatedBooking.getRegNum());
            booking.setServiceType(updatedBooking.getServiceType());
            booking.setPreferedDate(updatedBooking.getPreferedDate());
        });

        return existingBooking.orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable String id) {
        if (bookings.removeIf(booking -> booking.getId().equals(id))) {
            return "Booking deleted successfully.";
        }
        return "Booking not found.";
    }
}
