package Ella.task;

import java.time.LocalDateTime;


/**
 * Represents a todo task. It contains the description of
 * the task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     *{@inheritDoc}
     *
     * <p> As no date is associated with a {@link ToDo} an empty array is
     * returned. </p>
     * @return A empty array
     */
    @Override
    public LocalDateTime[] getDates() {
        return new LocalDateTime[0];
    }

    /**
     * {@inheritDoc}
     *
     * <p> As no dates are associated with {@link ToDo}, a large date is returned
     * as their order of importance is not high compared to tasks with clear deadline.</p>
     *
     * @return A date for comparison
     */
    public LocalDateTime compareDate() {
        return LocalDateTime.of(9999, 12, 31, 23, 59);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
