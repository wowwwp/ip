package Ella.command;

import Ella.utils.Storage;
import Ella.utils.TaskList;
import Ella.task.Task;

import java.io.IOException;

public class MarkCommand extends Command{
    private final int id;

    public MarkCommand(int id) {
        this.id = id;
    }
    @Override
    public void execute(Storage storage, TaskList taskList) throws IOException {
        if (taskList.checkTask(id)) {
            Task task = taskList.getTask(id);
            task.markAsDone();
            storage.updateTasks(taskList);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
