package Ella.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected final String description;
    private boolean isDone;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mma");

    public abstract LocalDateTime[] getDates();

    public abstract LocalDateTime compareDate();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setAsDone() {
        this.isDone = true;
        System.out.println("Wow someone was productive");
        System.out.println(this);
    }

    public void setAsUndone() {
        this.isDone = false;
        System.out.println("Well i guess this is a future you problem");
        System.out.println(this);
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }
}

