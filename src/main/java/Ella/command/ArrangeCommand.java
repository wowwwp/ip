package Ella.command;

import java.util.ArrayList;
import java.util.List;
import Ella.task.Task;
import Ella.task.TaskComparator;
import Ella.utils.Storage;
import Ella.utils.TaskList;

/**
 * Represents arrange command which arranges tasks that
 * are not marked as done and prints them out to the user.
 */
public class ArrangeCommand extends Command {
    /**
     * Prints all tasks which is not marked done arranged according
     * to the deadline in ascending order. If the task list is empty
     * or no incomplete tasks are present, it prints a different message
     *
     * @param tasks An ArrayList containing Task Objects
     */
    public void arrangeTasks(ArrayList<Task> tasks) {
        List<Task> sortedTasks = tasks.stream()
                .filter(task -> !task.isDone)
                .sorted(new TaskComparator())
                .toList();
        if (!sortedTasks.isEmpty()) {
            System.out.println("Ok here are your tasks arranged by deadline");
            for (Task task : sortedTasks) {
                System.out.println(task);
            }
        } else {
            System.out.println("You don't have any tasks to be arranged!!");
        }


    }

    /**
     * {@inheritDoc}
     *
     * Executes the arrange command by retrieving the ArrayList of Tasks
     * from {@code taskList} and calling the helper function.
     *
     * @param storage Handles saving and reading of tasks from JSON file
     * @param taskList An ArrayList containing tasks
     */
    @Override
    public void execute(Storage storage, TaskList taskList) {
        ArrayList<Task> tasks = taskList.getAllTasks();
        arrangeTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
