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
    /** Prints all the tasks present in {@code task}**/
    private void printTasks(ArrayList<Task> tasks) {
        System.out.println("This is what you got:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, tasks.get(i));
        }

    }

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
    public void execute(Storage storage, TaskList taskList) {
        ArrayList<Task> tasks = taskList.getAllTasks();
        printTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
