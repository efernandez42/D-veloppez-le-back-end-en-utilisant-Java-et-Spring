package com.rental.controller;

import com.rental.dto.RentalRequest;
import com.rental.model.Rental;
import com.rental.repository.RentalRepository;
import com.rental.service.RentalService;
import com.rental.service.RentalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private static final String UPLOAD_DIR = "src/main/resources/public/uploads/";

    @Autowired
    private RentalServiceImpl rentalServiceImpl;

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalServiceImpl.getAllRentals();
    }

    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable Long id) {
        return rentalServiceImpl.getRentalById(id);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Rental> createRental(@ModelAttribute RentalRequest rentalRequest) {
        try {
            MultipartFile file = rentalRequest.getPicture();
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            file.transferTo(Paths.get(UPLOAD_DIR + fileName));

            Rental rental = new Rental();
            rental.setName(rentalRequest.getName());
            rental.setSurface(rentalRequest.getSurface());
            rental.setPrice(rentalRequest.getPrice());
            rental.setPicture(fileName);
            rental.setDescription(rentalRequest.getDescription());
            rental.setOwnerId(1L);

            Rental savedRental = rentalServiceImpl.saveRental(rental);

            return ResponseEntity.ok(savedRental);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

//
//    @PostMapping(consumes = "multipart/form-data")
//    public Rental saveRental(@RequestBody Rental rental) {
//        return rentalService.saveRental(rental);
//    }
//