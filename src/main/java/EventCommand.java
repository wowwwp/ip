import java.time.LocalDateTime;

public class EventCommand extends Command {
    private final String[] args;

    public EventCommand(String[] args) {
        this.args = args;
    }
    public Event createEvent() {
        LocalDateTime from = parseTime(args[1]);
        LocalDateTime to = parseTime(args[2]);
        return new Event(args[0], from, to);
    }

    @Override
    void execute(Storage storage, TaskList taskList) {
        Event event = createEvent();
        taskList.process(event);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
