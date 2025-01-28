import java.util.ArrayList;

public class ListCommand extends Command {

    public void printTasks(ArrayList<Task> tasks) {
        System.out.println("This is what you got:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, tasks.get(i));
        }

    }

    @Override
    void execute(Storage storage, TaskList taskList) {
        ArrayList<Task> tasks = taskList.getAllTasks();
        printTasks(tasks);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
