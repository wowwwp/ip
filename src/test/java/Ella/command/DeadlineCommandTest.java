package ella.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import ella.task.Deadline;


public class DeadlineCommandTest {
    @Test
    public void testDeadlineCommand_success() {
        DeadlineCommand command = new DeadlineCommand(new String[]{"read books", "30/10/2002 1400"});
        Deadline d = command.createDeadline();
        assertEquals("[D][ ] read books (by: 30-10-2002 2:00PM)", d.toString());

    }

    @Test
    public void testDeadlineCommand_improperTime_fail() {
        DeadlineCommand command = new DeadlineCommand(new String[]{"read books", "30/10/2002"});
        DeadlineCommand commandOne = new DeadlineCommand(new String[]{"read books", "1400"});
        try {
            Deadline d = command.createDeadline();
            Deadline done = commandOne.createDeadline();
        } catch (DateTimeParseException e) {
            assertTrue(true);
        }

    }
}
