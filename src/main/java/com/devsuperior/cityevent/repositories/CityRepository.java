package com.devsuperior.cityevent.repositories;

import com.devsuperior.cityevent.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author dm
 */
public interface CityRepository extends JpaRepository<City, Long>
{
}
