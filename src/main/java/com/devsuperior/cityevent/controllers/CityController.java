package com.devsuperior.cityevent.controllers;

import com.devsuperior.cityevent.dto.CityDTO;
import com.devsuperior.cityevent.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityController
{
    @Autowired
    CityService service;

    /**
     * findAll
     *
     * @return ResponseEntity<List<CityDTO>>
     */
    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll()
    {
        List<CityDTO> cities = service.findAll();

        return ResponseEntity.ok().body( cities );
    }

    /**
     * insert
     *
     * @param dto CityDTO
     * @return ResponseEntity<CityDTO>
     */
    @PostMapping
    public ResponseEntity<CityDTO> insert( @RequestBody CityDTO dto )
    {
        dto = service.insert( dto );

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path( "/{id}" )
                                                                   .buildAndExpand( dto.getId() )
                                                                   .toUri();

        return ResponseEntity.created( uri ).body( dto );
    }

    /**
     * update
     *
     * @param id Long
     * @return ResponseEntity<CityDTO>
     */
    @DeleteMapping( value = "/{id}" )
    public ResponseEntity<Void> delete( @PathVariable Long id )
    {
        service.delete( id );

        return ResponseEntity.noContent().build();
    }
}
