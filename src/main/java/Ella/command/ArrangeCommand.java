package ella.command;

import java.util.ArrayList;
import java.util.List;

import ella.task.Task;
import ella.task.TaskComparator;
import ella.utils.Storage;
import ella.utils.TaskList;

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
    public String arrangeTasks(ArrayList<Task> tasks) {
        List<Task> sortedTasks = tasks.stream()
                .filter(task -> !task.isDone())
                .sorted(new TaskComparator())
                .toList();

        StringBuilder result = new StringBuilder();
        if (sortedTasks.isEmpty()) {
            result.append("Nice! looks like you have nothing to do!!\n");
            return result.toString();
        }

        result.append("Here are your unfinished tasks, arranged:\n");
        for (Task task : sortedTasks) {
            result.append(task).append("\n");
        }
        return result.toString();
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
    public String execute(Storage storage, TaskList taskList) {
        ArrayList<Task> tasks = taskList.getAllTasks();
        return arrangeTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
