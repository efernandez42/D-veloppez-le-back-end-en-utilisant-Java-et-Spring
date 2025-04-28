package com.rental.service;

import com.rental.model.Rental;

import java.util.List;

public interface RentalService {
    List<Rental> getAllRentals();
    Rental getRentalById(Long id);
    Rental saveRental(Rental rental);

}
