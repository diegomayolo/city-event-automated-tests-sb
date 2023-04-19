package com.devsuperior.cityevent.repositories;

import com.devsuperior.cityevent.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author dm
 */
public interface EventRepository extends JpaRepository<Event, Long>
{
}
