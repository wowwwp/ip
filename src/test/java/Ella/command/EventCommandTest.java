package Ella.command;

import Ella.task.Event;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventCommandTest {
    @Test
    public void createEventCommand_success() {
        EventCommand eventCommand = new EventCommand(new String[]{"test", "30/06/2002 1600", "21/07/2003 1400"});
        Event event = eventCommand.createEvent();
        assertEquals("[E][ ] test (from: 30-06-2002 4:00pm to: 21-07-2003 2:00pm)", event.toString());
    }
    @Test
    public void createEventCommand_fail() {
        EventCommand eventCommand = new EventCommand(new String[]{"test", "30/06/2002 1600", "21/07/2003"});
        try {
            eventCommand.createEvent();
            fail();
        } catch (DateTimeParseException e) {
            assertEquals("Text '21/07/2003' could not be parsed at index 10", e.getMessage());
        }

    }
}
