package Ella.command;

import java.io.IOException;

import Ella.utils.Storage;
import Ella.utils.TaskList;
import Ella.task.Task;


/**
 * Represents the delete command. It deletes a certain
 * task from the {@code taskList}
 */
public class DeleteCommand extends Command {
    private final int id;

    /**
     * Initializes a {@link DeadlineCommand} with the id
     * of the task to be deleted.
     *
     * @param id Index of the task which is to be deleted
     */
    public DeleteCommand(int id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     *
     *<p>Checks if a given index is a valid index and then deletes the task
     * from the {@code taskList}. The task is then removed from the JSON file.</p>
     *
     * @param storage Handles saving and loading tasks when taskList changes
     * @param taskList An ArrayList containing tasks
     * @throws IOException If there are issues saving the taskList in the JSON file
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws IOException {
        if (taskList.checkTask(id)) {
            Task task = taskList.getTask(id);
            String output = taskList.deleteTask(task);
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
