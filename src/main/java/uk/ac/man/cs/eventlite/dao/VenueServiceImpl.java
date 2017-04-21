package uk.ac.man.cs.eventlite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.man.cs.eventlite.entities.Venue;

@Service
public class VenueServiceImpl implements VenueService {

	@Autowired
	private VenueRepository venueRepository;

	@Override
	public void save(Venue venue) {
		venueRepository.save(venue);
	}

	@Override
	public Iterable<Venue> findAll() {
		return venueRepository.findAll();
	}
}
