package Ella.task;

import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

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

    @Override
    public LocalDateTime[] getDates() {
        return new LocalDateTime[]{from,to};
    }

    public LocalDateTime compareDate() {
        return from;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from.format(super.formatter), to.format(super.formatter));
    }
}
