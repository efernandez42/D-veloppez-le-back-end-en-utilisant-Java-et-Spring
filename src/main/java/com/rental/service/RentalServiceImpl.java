package com.rental.service;

import com.rental.model.Rental;
import com.rental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    private static final String UPLOAD_DIR = "uploads/";

    @Override
    public List<Rental> getAllRentals(){
        List<Rental> rentals = rentalRepository.findAll();

        for (Rental rental : rentals) {
            String fullImageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/" + UPLOAD_DIR)
                    .path(rental.getPicture())
                    .toUriString();
            rental.setPicture(fullImageUrl);
        }

        return rentals;
    }

    @Override
    public Rental getRentalById(Long id)
    {
        Rental rental =  rentalRepository.findById(id).get();

        String fullImageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/" + UPLOAD_DIR)
                .path(rental.getPicture())  // Ici on utilise getPicture() du Rental
                .toUriString();
        rental.setPicture(fullImageUrl);

        return rental;
    }

    @Override
    public Rental saveRental(@RequestBody Rental rental) {
        return rentalRepository.save(rental);
    }
}
