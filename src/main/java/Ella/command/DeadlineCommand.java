package Ella.command;

import java.io.IOException;
import java.time.LocalDateTime;

import Ella.utils.Storage;
import Ella.utils.TaskList;
import Ella.task.Deadline;

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
    public void execute(Storage storage, TaskList taskList) throws IOException {
        Deadline deadline = createDeadline();
        taskList.process(deadline);
        storage.updateTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
