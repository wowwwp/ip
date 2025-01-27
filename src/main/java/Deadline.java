import java.time.LocalDateTime;

public class Deadline extends Task{
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public LocalDateTime[] getDates() {
        return new LocalDateTime[]{by};
    }

    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(super.formatter));
    }

}
