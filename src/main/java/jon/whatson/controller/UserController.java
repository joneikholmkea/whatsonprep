package jon.whatson.controller;

import jon.whatson.model.User;
import jon.whatson.service.IUserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(path ="/createUser", consumes="application/json")
    public ResponseEntity<User> createUser(@RequestBody User user){
        String msg="";
        System.out.println("received user: " + user.getName());
        if(userService.save(user)!=null) {
            msg="Oprettet bruger: "+user.getName();
        }else {
            msg="fejl i oprettelse af " + user.getName();
        }
       return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findUserByName")
    public ResponseEntity<List<User>> findByName(String name){
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("Access-Control-Allow-Origin","+");
//        return ResponseEntity.ok()
//                .headers(responseHeaders)
//                .body(userService.findByName(name));
        return new ResponseEntity<>(userService.findByName(name),HttpStatus.OK);
    }
}
