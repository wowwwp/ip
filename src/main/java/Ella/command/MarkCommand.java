package Ella.command;

import java.io.IOException;

import Ella.utils.Storage;
import Ella.utils.TaskList;
import Ella.task.Task;


/**
 * Represents mark command which marks a given
 * task as done.
 */
public class MarkCommand extends Command{
    private final int id;

    /**
     * Initializes a {@link MarkCommand} with the id
     * of the task to be marked as done.
     *
     * @param id Index of the task which is to be marked as done
     */
    public MarkCommand(int id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     *
     *<p>Checks if a given index is a valid index and then obtains the task
     * from the {@code taskList}. The task is then marked as done. The JSON file
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
            String output = task.setAsDone();
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
