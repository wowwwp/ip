package ella.command;

import java.io.IOException;
import java.util.ArrayList;

import ella.task.Task;
import ella.utils.Storage;
import ella.utils.TaskList;

public class FindCommand extends Command {
    private final String find;

    public FindCommand(String find) {
        this.find = find;
    }


    @Override
    public String execute(Storage storage, TaskList taskList) throws IOException {
        ArrayList<Task> tasks = taskList.getAllTasks();
        StringBuilder result = new StringBuilder();

        result.append("Here are the matching tasks in your list:\n");

        for (Task task : tasks) {
            String taskDescription = task.getDescription();
            if (taskDescription.contains(find)) {
                result.append(task).append("\n");
            }
        }

        return result.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
