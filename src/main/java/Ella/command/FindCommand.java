package ella.command;

import java.io.IOException;
import java.util.ArrayList;

import ella.task.Task;
import ella.utils.Storage;
import ella.utils.TaskList;

/**
 * Represents the find command, which searches for tasks whose descriptions
 * contain a given substring provided by the user.
 */
public class FindCommand extends Command {
    private final String find;

    public FindCommand(String find) {
        this.find = find;
    }


    @Override
    public String execute(Storage storage, TaskList taskList) throws IOException {
        ArrayList<Task> tasks = taskList.getAllTasks();
        StringBuilder result = new StringBuilder();

        for (Task task : tasks) {
            String taskDescription = task.getDescription();
            if (taskDescription.contains(find)) {
                result.append(task).append("\n");
            }
        }

        if (result.isEmpty()) {
            result.append("No matching tasks found\n");
        } else {
            result.insert(0, "Here are the matching tasks:\n");
        }

        return result.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
