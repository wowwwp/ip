package Ella.command;

import Ella.utils.Storage;
import Ella.utils.TaskList;
import Ella.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(Storage storage, TaskList taskList) throws IOException {
        if (taskList.checkTask(id)) {
            Task task = taskList.getTask(id);
            taskList.deleteTask(task);
            storage.updateTasks(taskList);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
