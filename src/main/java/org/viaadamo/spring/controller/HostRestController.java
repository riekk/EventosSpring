package org.viaadamo.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.viaadamo.spring.dto.HostDTO;
import org.viaadamo.spring.entity.Host;
import org.viaadamo.spring.service.HostService;

import java.util.List;

@RestController
@RequestMapping("/host")
public class HostRestController implements HostApi {

    private final HostService service;
    public HostRestController(HostService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Host>> getHosts() {
        List<Host> hosts = service.findAll();
        return new ResponseEntity<>(hosts, HttpStatus.OK);
    }

    @GetMapping("/eventsnumber")
    public ResponseEntity<List<HostDTO>> getEventsByHost() {
        List<HostDTO> hostDTOS = service.getEventsCount();
        return new ResponseEntity<>(hostDTOS, HttpStatus.OK);
    }

    
    @GetMapping("/manager/{manager_name}")
    public ResponseEntity<List<Host>> getByManagerName(@PathVariable("manager_name") String name) {
        List<Host> host = service.getByManagerName(name);
        return new ResponseEntity<>(host, HttpStatus.OK);
    }
}
