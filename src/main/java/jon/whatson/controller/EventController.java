package jon.whatson.controller;

import jon.whatson.model.Band;
import jon.whatson.model.Event;
import jon.whatson.service.IBandService;
import jon.whatson.service.IEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class EventController {

    private IEventService eventService;
    private IBandService bandService;

    public EventController(IEventService eventService, IBandService bandService) {
        this.eventService = eventService;
        this.bandService = bandService;
    }

    @PostMapping("/createEvent")
    public ResponseEntity<String> createEvent(@RequestBody Event event,
                                              @RequestParam Long bandID){
        System.out.println("received event: " + event);
        Optional<Band> band_ = bandService.findById(bandID);
        if(band_.isPresent()){
            event.setBand(band_.get());
            eventService.save(event);
            return new ResponseEntity<>("ok at oprette event", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("band ikke fundet " + bandID, HttpStatus.OK);
        }
    }
}
