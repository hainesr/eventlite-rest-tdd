package uk.ac.man.cs.eventlite.dao;

import uk.ac.man.cs.eventlite.entities.Venue;

public interface VenueService {

	public void save(Venue venue);

	public Iterable<Venue> findAll();
}
