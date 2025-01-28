import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
        System.out.printf("Ok got ya...I will remove this from the list...%n%s%nYou have %d tasks left%n", task.toString(), tasks.size());
    }

    public boolean checkTask(Integer id) {
        if (id < 0 || id >= tasks.size()) {
            throw new InvalidCommand("That task does not exist.....");
        }
        return true;
    }

    public Task getTask(Integer id) {
        return tasks.get(id);
    }

    public void process(Task task) {
        tasks.add(task);
        System.out.println("Ok, I will add this in...");
        System.out.printf("%s%nYou have %d tasks in the list%n", task.toString(), tasks.size());
    }


    public ArrayList<Task> getAllTasks() {
        return tasks;
    }


}
