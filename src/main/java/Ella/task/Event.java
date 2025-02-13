package ella.task;

import java.time.LocalDateTime;


/**
 * Represents an event task. It contains the description of
 * the task, when the event starts as well as when the event ends.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }
    /**
     *{@inheritDoc}
     *
     * <p> An array containing when the {@link Event} starts and ends is
     * returned.</p>
     *
     * @return An array containing the start date and the end date
     */
    @Override
    public LocalDateTime[] getDates() {
        return new LocalDateTime[]{from, to};
    }
    /**
     * {@inheritDoc}
     *
     * <p> Returns when the {@link Event} starts</p>
     *
     * @return A date for comparison
     */
    @Override
    public LocalDateTime compareDate() {
        return from;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from.format(super.formatter),
                to.format(super.formatter));
    }
}
