package com.devsuperior.cityevent.controllers;

import com.devsuperior.cityevent.dto.EventDTO;
import com.devsuperior.cityevent.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author dm
 */
@RestController
@RequestMapping(value = "/events")
public class EventController
{
    @Autowired
    private EventService service;

    /**
     * findAll
     *
     * @return ResponseEntity<EventDTO>
     */
    @GetMapping
    public ResponseEntity<List<EventDTO>> findAll()
    {
        List<EventDTO> events = service.findAll();

        return ResponseEntity.ok().body( events );
    }

    /**
     * update
     *
     * @param id Long
     * @param dto EventDTO
     * @return ResponseEntity<EventDTO>
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<EventDTO> update( @PathVariable Long id, @RequestBody EventDTO dto )
    {
        dto = service.update( id, dto );

        return ResponseEntity.ok().body( dto );
    }
}
