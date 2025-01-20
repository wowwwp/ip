import java.util.ArrayList;
import java.util.Scanner;

public class Ella {

    public static void printLines() {
        int CONSOLE_WIDTH = 80;
        String line = "─".repeat(CONSOLE_WIDTH);
        System.out.println(line);
    }

    public static void greet() {
        System.out.println("Hi! This is Ella\nWhat do you need? I've got this.");
    }

    public static void exit() {
        System.out.println("Bye... I know you will come back soon!");
    }

    public static void process(Task task, ArrayList<Task> tasks) {
        tasks.add(task);
        System.out.printf("added: %s\n", task.getDescription());
    }

    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println("This is what you got");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, tasks.get(i));
        }

    }

    public static Task getTask(String id, ArrayList<Task> tasks) {
        return tasks.get(Integer.parseInt(id) - 1);
    }

    public static void main(String[] args) {
        printLines();
        greet();
        printLines();

        ArrayList<Task> tasks = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            String line = in.nextLine();
            String[] split = line.split(" ");
            String command = split[0];

            if (command.equals("bye")) {
                break;
            }

            switch (command) {
                case "mark":
                    Task taskDone = getTask(split[1], tasks);
                    printLines();
                    taskDone.markAsDone();
                    printLines();
                    break;
                case "unmark":
                    Task taskUndone = getTask(split[1], tasks);
                    printLines();
                    taskUndone.markAsUndone();
                    printLines();
                    break;
                case "list":
                    printLines();
                    printTasks(tasks);
                    printLines();
                    break;
                default:
                    printLines();
                    Task task = new Task(line);
                    process(task, tasks);
                    printLines();
            }
        }

        printLines();
        exit();
        printLines();
    }
}
