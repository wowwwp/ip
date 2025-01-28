package Ella.utils;

import Ella.errors.InvalidCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ParserTest {
    @Test
    public void parseDeadlineTest_success() {
        String[] testDeadline = {"deadline", "new books /by 30/10/2020 1600"};
        String[] expectedSplit = {"new books ", " 30/10/2020 1600"};
        assertArrayEquals(expectedSplit, Parser.parseDeadline(testDeadline));

    }

    @Test
    public void parseDeadlineTest_fail() {
        String[] testDeadlineNoTask = {"deadline", " /by 30/10/2020 1600"};
        String[] testDeadlineNoDate = {"deadline", "new books"};

        try {
            Parser.parseDeadline(testDeadlineNoTask);
            Parser.parseDeadline(testDeadlineNoDate);
        } catch (InvalidCommand e) {
            assertEquals("Uhh you don't have a task or a date after the /by field...", e.getMessage());
        }

    }

    @Test
    public void parseEventTest_success() {
        String[] testEvent = {"event", "return books /from 30/10/2020 1600 /to 29/11/2020 1900"};
        String[] expectedEvent = {"return books ", " 30/10/2020 1600 ", " 29/11/2020 1900"};
        assertArrayEquals(expectedEvent, Parser.parseEvent(testEvent));
    }

    @Test
    public void parseEventTest_fail_one() {
        String[] testEvent_one = {"event", " "};
        try {
            Parser.parseEvent(testEvent_one);
        } catch (InvalidCommand e) {
            assertEquals("Uhh you need to have a task, a date after the /from field and a date after the /to field...", e.getMessage());
        }


    }
    @Test
    public void parseEventTest_fail_two() {
        String[] testEvent_two = {"event", "watch movie "};
        String[] testEvent_three = {"event", "/from 30/10/2020 1600"};
        String[] testEvent_four = {"event", "/to 30/10/2023 1600"};
        try {
            Parser.parseEvent(testEvent_two);
            Parser.parseEvent(testEvent_three);
            Parser.parseEvent(testEvent_four);
        } catch (InvalidCommand e) {
            assertEquals("Uhh you don't have a task or a date after the /from field...", e.getMessage());
        }
    }

    @Test
    public void parseEventTest_fail_three() {
        String[] testEvent_five = {"event", "read books /from 30/10/2020 1600 /to "};
        String[] testEvent_six = {"event", "read books /from 06/05/2024 1700"};
        try {
            Parser.parseEvent(testEvent_five);
            Parser.parseEvent(testEvent_six);
        } catch (InvalidCommand e) {
            assertEquals("Uhh you don't have a date after the /from field or the /to field...", e.getMessage());
        }
    }
}
