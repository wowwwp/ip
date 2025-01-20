import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ella {

    public static void printLines() {
        int CONSOLE_WIDTH = 80;
        String line = "\u2500".repeat(CONSOLE_WIDTH);
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
        System.out.println("Ok, I will add this in...");
        System.out.printf("%s%nYou have %d tasks in the list%n", task.toString(), tasks.size());
    }

    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println("This is what you got:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, tasks.get(i));
        }

    }

    public static Task getTask(String id, ArrayList<Task> tasks) {
        return tasks.get(Integer.parseInt(id) - 1);
    }

    public static String[] parseDeadline(String line) {
        return line.split("/by");
    }

    public static String[] parseEvent(String line) {
        String[] splitOne = line.split("/from");
        String[] splitTwo = splitOne[1].split("/to");
        return new String[]{splitOne[0], splitTwo[0], splitTwo[1]};
    }

    public static void main(String[] args) {
        printLines();
        greet();
        printLines();

        ArrayList<Task> tasks = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            // parse command
            String line = in.nextLine();
            String[] split = line.split(" ", 2);
            String command = split[0];

            if (command.equals("bye")) {
                break;
            }

            switch (command) {
                case "todo":
                    printLines();
                    ToDo todo = new ToDo(split[1]);
                    process(todo, tasks);
                    printLines();
                    break;
                case "deadline":
                    printLines();
                    String[] parsedDeadline = parseDeadline(split[1]);
                    Deadline deadline = new Deadline(parsedDeadline[0], parsedDeadline[1]);
                    process(deadline, tasks);
                    printLines();
                    break;
                case "event":
                    printLines();
                    String[] parsedEvent = parseEvent(split[1]);
                    Event event = new Event(parsedEvent[0], parsedEvent[1], parsedEvent[2]);
                    process(event, tasks);
                    printLines();
                    break;
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
