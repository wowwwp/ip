package ella.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import ella.task.Event;

public class EventCommandTest {
    @Test
    public void createEventCommand_success() {
        EventCommand eventCommand = new EventCommand(new String[]{"test", "30/06/2002 1600", "21/07/2003 1400"});
        Event event = eventCommand.createEvent();
        assertEquals("[E][ ] test (from: 30-06-2002 4:00PM to: 21-07-2003 2:00PM)", event.toString());
    }

    @Test
    public void createEventCommand_improperTime_fail() {
        EventCommand eventCommand = new EventCommand(new String[]{"test", "30/06/2002 1600", "21/07/2003"});
        try {
            eventCommand.createEvent();
            fail();
        } catch (DateTimeParseException e) {
            assertTrue(true);
        }

    }
}
