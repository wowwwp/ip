package Ella.command;

import java.util.ArrayList;

import Ella.utils.Storage;
import Ella.utils.TaskList;
import Ella.task.Task;

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

        result.append("This is what you got:\n");

        for (int i = 0; i < tasks.size(); i++) {
            result.append(String.format("%d.%s\n", i + 1, tasks.get(i)));
        }

        return result.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
