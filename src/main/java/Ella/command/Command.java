package Ella.command;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Ella.utils.Storage;
import Ella.utils.TaskList;

/**
 * Represents a generic command in the system.
 *
 * <p>This abstract class defines the blueprint for all commands.
 * Subclasses must implement the {@link #execute(Storage, TaskList)}
 * and {@link #isExit()} methods to provide specific functionality.</p>
 *
 * <p>All known commands in the application are required to extend this class,
 * ensuring a consistent structure and behavior across different command types.</p>
 */
public abstract class Command {
    /**
     * Exectes the main functionality of the command
     *
     * @param storage Handles saving and loading tasks when taskList changes
     * @param taskList An ArrayList containing tasks
     * @throws IOException If an error occurs when saving tasks to the taskList
     */
    public abstract void execute(Storage storage, TaskList taskList) throws IOException;

    /**
     * Indicates if the chatbot needs to exit after the command
     */
    public abstract boolean isExit();

    /**
     * Parses time from a String to a LocalDateTime object. It  only accepts time
     * in dd/MM/yyyy HHmm format. Any time after 31/12/3035 would result in an
     * exception
     *
     * @param time A String representing time
     * @return LocalDateTime object representing time
     * @throws DateTimeException If the time is after 31/12/2035 2359
     * @throws java.time.format.DateTimeParseException If the time is not in the given format
     */
    public static LocalDateTime parseTime(String time) throws DateTimeException {
        time = time.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime date = LocalDateTime.parse(time, formatter);
        LocalDateTime MAX_DATE = LocalDateTime.of(3035, 12, 31, 23, 59);
        if (date.isAfter(MAX_DATE)) {
            throw new DateTimeException("That is way too far ahead come on....");
        }
        return date;
    }

}
