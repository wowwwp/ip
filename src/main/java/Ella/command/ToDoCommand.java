package Ella.command;

import java.io.IOException;

import Ella.utils.Storage;
import Ella.utils.TaskList;
import Ella.task.ToDo;


/**
 * Represents todo command which creates a new {@link ToDo}
 * and updates the JSON file.
 */
public class ToDoCommand extends Command{
    private final String description;

    /**
     * Initializes a {@link ToDoCommand}.
     *
     * @param description A String containing the task description.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     *
     * <p>It creates a new {@link ToDo} and adds it to the taskList. The new
     * {@link ToDo} is saved into the JSON file.</p>
     *
     * @param storage Handles saving and loading tasks when taskList changes
     * @param taskList An ArrayList containing tasks
     * @throws IOException When {@code taskList} cannot be saved into JSON file
     */
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
