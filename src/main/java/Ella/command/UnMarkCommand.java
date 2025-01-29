package Ella.command;

import java.io.IOException;

import Ella.utils.Storage;
import Ella.utils.TaskList;
import Ella.task.Task;


public class UnMarkCommand extends Command {
    private final int id;

    public UnMarkCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(Storage storage, TaskList taskList) throws IOException {
        if (taskList.checkTask(id)) {
            Task task = taskList.getTask(id);
            task.setAsUndone();
            storage.updateTasks(taskList);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
