package com.rental.service;

import com.rental.model.Rental;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RentalService {
    List<Rental> getAllRentals();
    Rental getRentalById(Long id);
    Rental saveRental(Rental rental);

}
