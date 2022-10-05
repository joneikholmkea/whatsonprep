package jon.whatson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class WhatsonApplication {

    public static void main(String[] args) {
//        UUID uuid = UUID.randomUUID();
//        System.out.println("unique key: ");
//        System.out.println(uuid.toString());
        SpringApplication.run(WhatsonApplication.class, args);
    }

}
