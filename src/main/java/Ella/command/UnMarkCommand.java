package ella.command;

import java.io.IOException;

import ella.task.Task;
import ella.utils.Storage;
import ella.utils.TaskList;


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
     * <p>Checks if a given index is a valid index and then unmarks the task.
     * The JSON file gets updated with the changes.</p>
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
