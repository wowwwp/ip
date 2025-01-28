package Ella.command;

import Ella.utils.Storage;
import Ella.utils.TaskList;
import Ella.task.ToDo;

import java.io.IOException;

public class ToDoCommand extends Command{
    private final String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Storage storage, TaskList taskList) throws IOException {
        ToDo toDo = new ToDo(description);
        taskList.process(toDo);
        storage.updateTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
