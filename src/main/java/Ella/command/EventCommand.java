package Ella.command;

import java.io.IOException;
import java.time.LocalDateTime;
import Ella.utils.Storage;
import Ella.utils.TaskList;
import Ella.task.Event;

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
    public void execute(Storage storage, TaskList taskList) throws IOException {
        Event event = createEvent();
        taskList.process(event);
        storage.updateTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
