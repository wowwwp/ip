package ella.command;

import java.util.ArrayList;

import ella.task.Task;
import ella.utils.Storage;
import ella.utils.TaskList;

/**
 * Represents list command which lists all the
 * tasks in the order the user has input them.
 */
public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     *
     * Executes the list command by retrieving the ArrayList of Tasks
     * from {@code taskList} and calling the helper function.
     *
     * @param storage Handles saving and reading of tasks from JSON file
     * @param taskList An ArrayList containing tasks
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        ArrayList<Task> tasks = taskList.getAllTasks();

        StringBuilder result = new StringBuilder();


        for (int i = 0; i < tasks.size(); i++) {
            result.append(String.format("%d.%s\n", i + 1, tasks.get(i)));
        }

        if (!result.isEmpty()) {
            result.insert(0, "This is what you got:\n");
        } else {
            result.append("You have no tasks for now\n");
        }

        return result.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
