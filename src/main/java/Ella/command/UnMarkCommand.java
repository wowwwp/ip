package Ella.command;

import java.io.IOException;

import Ella.utils.Storage;
import Ella.utils.TaskList;
import Ella.task.Task;


/**
 * Represents unmark command which unmarks a given
 * task as not done.
 */
public class UnMarkCommand extends Command {
    private final int id;

    /**
     * Initializes a {@link UnMarkCommand} with the id
     * of the task to be unmarked.
     *
     * @param id Index of the task which is to be unmarked.
     */
    public UnMarkCommand(int id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     *
     *<p>Checks if a given index is a valid index and then obtains the task
     * from the {@code taskList}. The task is then unmarked as done. The JSON file
     * gets updated with the changes.</p>
     *
     * @param storage Handles saving and loading tasks when taskList changes
     * @param taskList An ArrayList containing tasks
     * @throws IOException If there are issues saving the taskList in the JSON file
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws IOException {
        if (taskList.checkTask(id)) {
            Task task = taskList.getTask(id);
            String output = task.setAsUndone();
            storage.updateTasks(taskList);
            return output;
        }
        assert !taskList.checkTask(id);
        return "";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
