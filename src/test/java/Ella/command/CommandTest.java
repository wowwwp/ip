package ella.command;

import static ella.command.Command.parseTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class CommandTest {
    @Test
    public void testParseTime_success() {
        String testTimeFormatOne = "15/12/2020 1400";
        String testTimeFormatTwo = "15/12/2020 2.00PM";
        LocalDateTime targetTime = LocalDateTime.of(2020, 12, 15, 14, 00, 00);

        assertEquals(targetTime, parseTime(testTimeFormatOne));
        assertEquals(targetTime, parseTime(testTimeFormatTwo));
    }

    @Test
    public void testParseTime_error() {
        String testTimeFormatOne = "15/12/23 4.23pm";
        try {
            parseTime(testTimeFormatOne);
        } catch (DateTimeParseException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testParseTime_wrongCase_error() {
        String testTimeFormatOne = "15/12/2023 4.23pm";
        try {
            parseTime(testTimeFormatOne);
        } catch (DateTimeParseException e) {
            assertTrue(true);
        }
    }


}
