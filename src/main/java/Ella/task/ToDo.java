package Ella.task;

import java.time.LocalDateTime;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public LocalDateTime[] getDates() {
        return new LocalDateTime[0];
    }

    public LocalDateTime compareDate() {
        return LocalDateTime.of(9999, 12, 31, 23, 59);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
