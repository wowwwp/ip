package ella.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import ella.errors.InvalidCommand;

/**
 * Represents a Task.
 *
 * <p>This class provides common methods for marking tasks as done or undone.
 * Subclasses are required to implement specific methods for returning of relevant dates as each task
 * will have a different number of dates associated with them.</p>
 *
 * <p>All known tasks in the application are required to extend this class,
 * ensuring a consistent structure and behavior across different command types.</p>
 */
public abstract class Task {
    protected final String description;
    protected DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("dd-MM-yyyy h:mma")
            .toFormatter(Locale.ENGLISH);
    private boolean isDone;

    /**
     * Instantiates a {@link Task}
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Initializes a new {@link Task}. Used when loading in tasks
     * from the JSON file.
     *
     * @param description Description of the task
     * @param isDone boolean to signify if it is done
     */
    public Task(String description, boolean isDone) {
        this.description = description.trim();
        this.isDone = isDone;
    }

    // abstract methods
    /**
     * Returns all dates associated with the given task for saving them into a JSON file.
     *
     * @return An array containing dates
     */
    public abstract LocalDateTime[] getDates();

    /**
     * Returns a date that can be used to compare tasks. This date is used to determine
     * the order of tasks based on their deadlines.
     *
     * @return A date used for comparison
     */
    public abstract LocalDateTime compareDate();

    /**
     * Checks if a task which is done is getting marked again
     */
    public void checkDone() {
        if (isDone) {
            throw new InvalidCommand("You can't keep marking a task done....");
        }
    }

    /**
     * Checks if a task which is undone is getting unmarked again
     */
    public void checkUndone() {
        if (!isDone) {
            throw new InvalidCommand("You can't unmark something which is not done....");
        }
    }

    public String setAsDone() {
        checkDone();
        this.isDone = true;
        String output = "Wow someone was productive\n"
                + this + "\n";
        return output;
    }

    public String setAsUndone() {
        checkUndone();
        this.isDone = false;
        String output = "Well i guess this is a future you problem\n"
                + this + "\n";
        return output;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }
}

