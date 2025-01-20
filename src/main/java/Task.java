public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Wow someone was productive\n");
        System.out.println(this);
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println("Well i guess this is a future you problem\n");
        System.out.println(this);
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

