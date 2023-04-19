package com.devsuperior.cityevent.services;

import com.devsuperior.cityevent.dto.CityDTO;
import com.devsuperior.cityevent.dto.EventDTO;
import com.devsuperior.cityevent.entities.City;
import com.devsuperior.cityevent.entities.Event;
import com.devsuperior.cityevent.repositories.CityRepository;
import com.devsuperior.cityevent.repositories.EventRepository;
import com.devsuperior.cityevent.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dm
 */
@Service
public class EventService
{
    @Autowired
    EventRepository eventRepository;

    @Autowired
    CityRepository cityRepository;

    /**
     * findAll
     *
     * @return EventDTO
     */
    @Transactional( readOnly = true )
    public List<EventDTO> findAll()
    {
        List<Event> events = eventRepository.findAll();

        return events.stream().map( event -> new EventDTO( event ) ).collect( Collectors.toList() );
    }

    /**
     * update
     *
     * @param id
     * @param dto
     * @return EventDTO
     */
    @Transactional
    public EventDTO update( Long id, EventDTO dto )
    {
        try
        {
            Event event = eventRepository.getOne( id );
            event.setName( dto.getName() );
            event.setDate( dto.getDate() );
            event.setUrl( dto.getUrl() );
            event.setCity( cityRepository.getOne( dto.getCityId() ) );

            return new EventDTO( event );
        }

        catch ( EntityNotFoundException e )
        {
            throw new ResourceNotFoundException( "Id not found " + id );
        }
    }
}
