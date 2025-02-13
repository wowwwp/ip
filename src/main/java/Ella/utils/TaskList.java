package ella.utils;

import java.util.ArrayList;

import ella.task.Task;
import ella.errors.InvalidCommand;

/**
 * Encapsulates all functionalities related to adding, deleting and checking if valid tasks are present.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Deletes a task from the task list.
     * @param task Task which needs to be removed
     */
    public String deleteTask(Task task) {
        tasks.remove(task);
        StringBuilder result = new StringBuilder();

        result.append(String.format("Ok got ya...I will remove this from the list...\n%s\nYou have %d tasks left\n",
                task.toString(), tasks.size()));

        return result.toString();
    }

    /**
     * Checks if the given task index exists in the ArrayList of tasks.
     *
     * @param id Index of the task
     * @return True if the given index is present
     * @throws InvalidCommand If the task index does not exist
     */
    public boolean checkTask(Integer id) throws InvalidCommand {
        if (id < 0 || id >= tasks.size()) {
            throw new InvalidCommand("That task does not exist.....");
        }
        return true;
    }

    public Task getTask(Integer id) {
        return tasks.get(id);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task {@link Task} which needs to added into the ArrayList.
     */
    public String process(Task task) {
        tasks.add(task);
        StringBuilder result = new StringBuilder();

        result.append("Ok, I will add this in...\n");
        result.append(String.format("%s%nYou have %d tasks in the list%n", task.toString(), tasks.size()));
        return result.toString();
    }


    public ArrayList<Task> getAllTasks() {
        return tasks;
    }


}
