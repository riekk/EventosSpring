package org.viaadamo.spring.controller;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.viaadamo.spring.controller.util.EventFieldSort;
import org.viaadamo.spring.controller.util.JSONField;
import org.viaadamo.spring.entity.Event;
import org.viaadamo.spring.service.JsonService;

import java.util.List;

@RestController
@RequestMapping("/json")
public class JSONRestController implements JSONApi {

    private final JsonService service;

    public JSONRestController(JsonService service) {
        this.service = service;
    }

    @GetMapping("/path")
    public ResponseEntity<String> getEventPage(@RequestParam(name = "Entity") JSONField entity) {

        return new ResponseEntity<>(service.getPath(entity), HttpStatus.OK);
    }
}
