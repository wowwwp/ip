package Ella.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import Ella.errors.InvalidCommand;

public class ParserTest {
    @Test
    public void parseDeadlineTest_properSplit_success() {
        String[] testDeadline = {"deadline", "new books /by 30/10/2020 1600"};
        String[] expectedSplit = {"new books ", " 30/10/2020 1600"};
        assertArrayEquals(expectedSplit, Parser.parseDeadline(testDeadline));

    }

    @Test
    public void parseDeadlineTest_missingInformation_fail() {
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
    public void parseEventTest_properSplit_success() {
        String[] testEvent = {"event", "return books /from 30/10/2020 1600 /to 29/11/2020 1900"};
        String[] expectedEvent = {"return books ", " 30/10/2020 1600 ", " 29/11/2020 1900"};
        assertArrayEquals(expectedEvent, Parser.parseEvent(testEvent));
    }

    @Test
    public void parseEventTest_missingTask_fail() {
        String[] testEventMissingTask = {"event", " "};
        try {
            Parser.parseEvent(testEventMissingTask);
        } catch (InvalidCommand e) {
            assertEquals("Uhh you need to have a task, a date after the /from field " +
                            "and a date after the /to field...", e.getMessage());
        }

    }

    @Test
    public void parseEventTest_missingTaskOrFrom_fail() {
        String[] testEventMissingFrom = {"event", "watch movie "};
        String[] testEventMissingTask = {"event", "/from 30/10/2020 1600"};
        String[] testEventMissingBoth = {"event", "/to 30/10/2023 1600"};
        try {
            Parser.parseEvent(testEventMissingFrom);
            Parser.parseEvent(testEventMissingTask);
            Parser.parseEvent(testEventMissingBoth);
        } catch (InvalidCommand e) {
            assertEquals("Uhh you don't have a task or a date after the /from field...", e.getMessage());
        }
    }

    @Test
    public void parseEventTest_missingFromOrTo_fail() {
        String[] testEventMissingDate = {"event", "read books /from 30/10/2020 1600 /to "};
        String[] testEventMissingTo = {"event", "read books /from 06/05/2024 1700"};
        try {
            Parser.parseEvent(testEventMissingDate);
            Parser.parseEvent(testEventMissingTo);
        } catch (InvalidCommand e) {
            assertEquals("Uhh you don't have a date after the /from field or the /to field...",
                    e.getMessage());
        }
    }
}
