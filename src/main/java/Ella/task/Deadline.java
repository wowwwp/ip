package ella.task;

import java.time.LocalDateTime;


/**
 * Represents a deadline task. It contains the description of
 * the task as well as a due date.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Initializes a {@link Deadline}. Used to create new tasks.
     * @param description task description
     * @param by deadline for the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Initializes a {@link Deadline}. Used to load existing tasks
     * from JSON file.
     * @param description task description
     * @param isDone boolean representing if a task is marked as done
     * @param by deadline for the task
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * {@inheritDoc}
     *
     * <p> An array containing the due date of the {@link Deadline} is
     * returned.</p>
     *
     * @return An array containing the due date
     */
    @Override
    public LocalDateTime[] getDates() {
        return new LocalDateTime[]{by};
    }

    /**
     * {@inheritDoc}
     *
     * <p> Returns the due date associated with {@link Deadline}. </p>
     *
     * @return A date for comparison
     */
    public LocalDateTime compareDate() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(super.formatter));
    }

}
