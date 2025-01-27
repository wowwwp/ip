public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String[] getDates() {
        return new String[0];
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
