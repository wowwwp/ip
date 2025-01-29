package Ella.task;

import java.time.LocalDateTime;


/**
 * Represents a deadline task. It contains the description of
 * the task as well as a due date.
 */

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     *{@inheritDoc}
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
