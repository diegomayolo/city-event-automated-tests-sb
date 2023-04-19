package com.devsuperior.cityevent.services;

import com.devsuperior.cityevent.dto.CityDTO;
import com.devsuperior.cityevent.entities.City;
import com.devsuperior.cityevent.repositories.CityRepository;
import com.devsuperior.cityevent.services.exceptions.DatabaseException;
import com.devsuperior.cityevent.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 *
 * @author dm
 */
@Service
public class CityService
{
    @Autowired
    CityRepository repository;

    /**
     * findAll
     *
     * @return List<CityDTO>
     */
    @Transactional( readOnly = true )
    public List<CityDTO> findAll()
    {
        List<City> cities = repository.findAll( Sort.by( "name" ) );

        return cities.stream().map( city -> new CityDTO( city ) ).collect( java.util.stream.Collectors.toList() );
    }

    /**
     * insert
     *
     * @param dto CityDTO
     * @return CityDTO
     */
    @Transactional
    public CityDTO insert( CityDTO dto )
    {
        City city = new City();
        city.setName( dto.getName() );

        city = repository.save( city );

        return new CityDTO( city );
    }

    public void delete( Long id )
    {
        try
        {
             repository.deleteById( id );
        }

        catch ( EmptyResultDataAccessException e )
        {
            throw new ResourceNotFoundException( "Id not found " + id );
        }

        catch ( DataIntegrityViolationException e )
        {
            throw new DatabaseException( "Integrity violation" );
        }
    }
}
