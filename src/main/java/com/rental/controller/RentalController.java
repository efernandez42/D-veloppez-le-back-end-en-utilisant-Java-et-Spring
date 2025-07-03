package com.rental.controller;

import com.rental.dto.RentalRequest;
import com.rental.model.Rental;
import com.rental.model.User;
import com.rental.repository.RentalRepository;
import com.rental.service.RentalService;
import com.rental.service.RentalServiceImpl;
import com.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private static final String UPLOAD_DIR = "src/main/resources/public/uploads/";

    @Autowired
    private UserService userService;

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
    public Map<String, String> createRental(@RequestHeader("Authorization") String token, @ModelAttribute RentalRequest rentalRequest) {
        try {
            MultipartFile file = rentalRequest.getPicture();
            String fileName = null;

            if (file != null && !file.isEmpty()) {
                fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                file.transferTo(Paths.get(UPLOAD_DIR + fileName));
            }

            String cleanToken = token.replace("Bearer ", "");
            User user = userService.getUserFromToken(cleanToken);

            Rental rental = new Rental();
            rental.setName(rentalRequest.getName());
            rental.setSurface(rentalRequest.getSurface());
            rental.setPrice(rentalRequest.getPrice());
            rental.setPicture(fileName);
            rental.setDescription(rentalRequest.getDescription());
            rental.setOwnerId(user.getId());

            Rental savedRental = rentalServiceImpl.saveRental(rental);

            return Map.of("message", "Rental created !");

        } catch (IOException e) {
            return Map.of("error", ResponseEntity.internalServerError().build().toString()) ;
        }
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public Map<String, String> updateRental(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @ModelAttribute RentalRequest rentalRequest) {

        try {
            Rental existingRental = rentalServiceImpl.getRentalById(id);
            if (existingRental == null) {
                return Map.of("error", "Location non trouvée");
            }

            String cleanToken = token.replace("Bearer ", "");
            User user = userService.getUserFromToken(cleanToken);

            if (!existingRental.getOwnerId().equals(user.getId())) {
                return Map.of("error", "Vous n'êtes pas autorisé à modifier cette location.");
            }

            existingRental.setName(rentalRequest.getName());
            existingRental.setSurface(rentalRequest.getSurface());
            existingRental.setPrice(rentalRequest.getPrice());
            existingRental.setDescription(rentalRequest.getDescription());

            rentalServiceImpl.updateRental(id, existingRental);

            return Map.of("message", "Location mise à jour avec succès.");
        } catch (Exception e) {
            return Map.of("error", "Une erreur est survenue : " + e.getMessage());
        }
    }





}
