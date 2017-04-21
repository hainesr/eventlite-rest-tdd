package uk.ac.man.cs.eventlite.dao;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import uk.ac.man.cs.eventlite.TestParent;
import uk.ac.man.cs.eventlite.entities.Event;

public class EventServiceTest extends TestParent {

	@Autowired
	private EventService eventService;

	@Test
	public void findAll() {
		List<Event> events = (List<Event>) eventService.findAll();
		long count = eventService.count();

		assertThat("findAll should get all events.", count, equalTo((long) events.size()));
	}

	@Test
	public void saveEvent() {
		long count = eventService.count();

		Event event = new Event();
		eventService.save(event);

		assertThat("Saved event count should have increased by one.", (count + 1), equalTo(eventService.count()));
		assertThat("Saved Event ID should not be zero.", 0L, not(equalTo(event.getId())));
	}
}
