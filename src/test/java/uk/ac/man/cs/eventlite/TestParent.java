package uk.ac.man.cs.eventlite;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventLite.class)
@Transactional
@ActiveProfiles("test")
public abstract class TestParent {

}
