package Ella.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task.
 *
 * <p>This abstract class defines the blueprint for all tasks.
 * Subclasses must contain which represent the description of the task
 * as well as boolean representing if a task is complete.</p>
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
    private boolean isDone;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mma");
    // abstract methods
    /**
     * Returns all dates associated with the given task
     * @return An array containing dates
     */
    public abstract LocalDateTime[] getDates();

    /**
     * Returns a date that can be used to compare tasks. This date is typically used to determine
     * the order or priority of tasks based on their deadlines.
     *
     * @return A date used for comparison
     */
    public abstract LocalDateTime compareDate();

    public Task(String description) {
        this.description = description;
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
        this.description = description;
        this.isDone = isDone;
    }

    public String setAsDone() {
        this.isDone = true;

        String result = "Wow someone was productive\n" +
                this + "\n";
        return result;
    }

    public String setAsUndone() {
        this.isDone = false;
        System.out.println("Well i guess this is a future you problem");
        System.out.println(this);
        String result = "Well i guess this is a future you problem\n" +
                this + "\n";
        return result;
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

