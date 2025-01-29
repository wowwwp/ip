package Ella.command;

import java.io.IOException;
import java.util.ArrayList;

import Ella.task.Task;
import Ella.utils.Storage;
import Ella.utils.TaskList;

public class FindCommand extends Command {
    private String find;

    public FindCommand(String find) {
        this.find = find;
    }


    @Override
    public void execute(Storage storage, TaskList taskList) throws IOException {
        ArrayList<Task> tasks = taskList.getAllTasks();
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            String taskDescription = task.getDescription();
            if (taskDescription.contains(find)) {
                System.out.println(task);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
