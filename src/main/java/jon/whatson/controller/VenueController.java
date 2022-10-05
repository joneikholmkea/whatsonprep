package jon.whatson.controller;

import jon.whatson.model.User;
import jon.whatson.model.Venue;
import jon.whatson.repository.UserRepository;
import jon.whatson.repository.VenueRepository;
import jon.whatson.service.EventService;
import jon.whatson.service.UserService;
import jon.whatson.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VenueController {

    private VenueService venueService;
    private UserService userService;

    public VenueController(VenueService venueService, UserService userService) {
        this.venueService = venueService;
        this.userService = userService;
    }

    @PostMapping("/createVenue")
    public ResponseEntity<Venue> createVenue(@RequestBody Venue venue){
        venueService.save(venue);
        return new ResponseEntity<>(venue, HttpStatus.OK);
    }

    @PostMapping("/createVenueLike")
    public ResponseEntity<String> createVenueLike(@RequestParam Long venueID, @RequestParam Long userID){
        Optional<User> user_ = userService.findById(userID);
        System.out.println("found user: " + user_);
        Optional<Venue> venue_ = venueService.findById(venueID);
        System.out.println("found venue : " + venue_);
        if(user_.isPresent() && venue_.isPresent()){
            user_.get().getLikedVenues().add(venue_.get());
            userService.save(user_.get()); // Venue > (mappedBy = "likedVenues") = don't need to call venueRepository.save(venue_get()) here.
            return new ResponseEntity<>("ok saving user:" + userID + " who likes venue: " + venueID, HttpStatus.OK);
        }
        return new ResponseEntity<>("unable to find user and/or venue", HttpStatus.BAD_REQUEST);
    }
}
