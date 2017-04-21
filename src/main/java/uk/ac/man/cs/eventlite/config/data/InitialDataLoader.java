package uk.ac.man.cs.eventlite.config.data;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import uk.ac.man.cs.eventlite.dao.EventService;
import uk.ac.man.cs.eventlite.dao.VenueService;
import uk.ac.man.cs.eventlite.entities.Event;
import uk.ac.man.cs.eventlite.entities.Venue;

@Component
@Profile({ "default", "test" })
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private final static Logger log = LoggerFactory.getLogger(InitialDataLoader.class);

	private final static String VENUE = "Kilburn Building, G23";
	private final static String[][] EVENTS = { { "COMP23420 Showcase, group Q", "2017-05-12T11:00:00+01:00" },
			{ "COMP23420 Showcase, group R", "2017-05-11T11:00:00+01:00" },
			{ "COMP23420 Showcase, group T", "2017-05-12T09:00:00+01:00" } };

	@Autowired
	private EventService eventService;

	@Autowired
	private VenueService venueService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (eventService.count() > 0) {
			log.info("Database already populated. Skipping data initialization.");
			return;
		}

		Venue venue = new Venue();
		venue.setName(VENUE);
		venue.setCapacity(80);
		venueService.save(venue);

		log.info("Created Venue: " + VENUE + ".");

		for (String[] s : EVENTS) {
			eventService.save(createEvent(s[0], s[1], venue));
		}
	}

	private Event createEvent(String name, String date, Venue venue) {
		Event event = new Event();
		event.setName(name);
		event.setDate(parseDate(date));
		event.setVenue(venue);

		log.info("Created Event: " + name + ".");

		return event;
	}

	private Date parseDate(String date) {
		Date d;

		try {
			ISO8601DateFormat df = new ISO8601DateFormat();
			d = df.parse(date);
		} catch (ParseException e) {
			log.error("Cannot parse date; using current date/time.");
			d = new Date();
		}

		return d;
	}
}
