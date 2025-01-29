package Ella.command;

import java.util.ArrayList;
import java.util.List;

import Ella.task.Task;
import Ella.task.TaskComparator;
import Ella.utils.Storage;
import Ella.utils.TaskList;


public class ArrangeCommand extends Command {

    public void arrangeTasks(ArrayList<Task> tasks) {
        List<Task> sortedTasks = tasks.stream()
                .filter(task -> !task.isDone())
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
