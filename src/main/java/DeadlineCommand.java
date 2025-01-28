import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private final String[] splits;

    public DeadlineCommand(String[] splits) {
        this.splits = splits;
    }
    public Deadline createDeadline() {
        LocalDateTime by = parseTime(splits[1]);
        return new Deadline(splits[0], by);
    }
    @Override
    void execute(Storage storage, TaskList taskList) {
        Deadline deadline = createDeadline();
        taskList.process(deadline);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
