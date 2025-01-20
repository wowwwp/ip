import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ella {

    public static void printLines() {
        int CONSOLE_WIDTH = 80;
        String line = "-".repeat(CONSOLE_WIDTH);
        System.out.println(line);
    }

    public static void greet() {
        printLines();
        System.out.println("Hi! This is Ella\nWhat do you need? I've got this.");
        printLines();
    }

    public static void exit() {
        printLines();
        System.out.println("Bye... I know you will come back soon!");
        printLines();
    }

    public static void process(Task task, ArrayList<Task> tasks) {
        tasks.add(task);
        printLines();
        System.out.println("Ok, I will add this in...");
        System.out.printf("%s%nYou have %d tasks in the list%n", task.toString(), tasks.size());
        printLines();
    }

    public static void printTasks(ArrayList<Task> tasks) {
        printLines();
        System.out.println("This is what you got:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, tasks.get(i));
        }
        printLines();

    }

    public static void checkValidity(String[] split, String message) throws InvalidCommand {
        if (split.length != 2) {
            throw new InvalidCommand(message);
        }
    }

    public static Task getTask(String[] split, ArrayList<Task> tasks) throws InvalidCommand{
        checkValidity(split, "You need to give me a valid task number....");
        int id = Integer.parseInt(split[1]);
        if (id < 0 || id > tasks.size()) {
            throw new InvalidCommand("That task does not exist.....");
        }
        return tasks.get(id - 1);
    }

    public static String parseToDo(String[] splits) throws IndexOutOfBoundsException{
        checkValidity(splits, "You need to have a task for todo!!");
        return splits[1];
    }
    public static String[] parseDeadline(String[] splits) throws IndexOutOfBoundsException{
        // Check if task is present
        checkValidity(splits, "You need to have a task for deadline!!");
        // Check if deadline is present
        String[] splitsDeadline = splits[1].split("/by");
        checkValidity(splitsDeadline, "You need to have a time after the /by field!!");
        return splitsDeadline;
    }

    public static String[] parseEvent(String[] splits) {
        // Check if task is present
        checkValidity(splits, "You need to have a task for event!!");
        // Check if from for the task is present
        String[] splitOne = splits[1].split("/from");
        checkValidity(splitOne, "You need to have a time after the /from field!!");
        // Check if to for the task is present
        String[] splitTwo = splitOne[1].split("/to");
        checkValidity(splitTwo, "You need to have a time after the /to field!!");
        return new String[]{splitOne[0], splitTwo[0], splitTwo[1]};
    }

    public static void printErrors(Exception e) {
        printLines();
        System.out.println(e.getMessage());
        printLines();
    }


    public static void main(String[] args) {
        greet();

        // Initialize array to store tasks
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        while(in.hasNextLine()) {
            try {
                // parse command
                String line = in.nextLine();
                String[] split = line.split(" ", 2);
                String command = split[0];

                if (command.equals("bye")) {
                    break;
                }

                switch (command) {
                    case "todo":
                        String description = parseToDo(split);
                        ToDo todo = new ToDo(description);
                        process(todo, tasks);
                        break;
                    case "deadline":
                        String[] parsedDeadline = parseDeadline(split);
                        Deadline deadline = new Deadline(parsedDeadline[0], parsedDeadline[1]);
                        process(deadline, tasks);
                        break;
                    case "event":
                        String[] parsedEvent = parseEvent(split);
                        Event event = new Event(parsedEvent[0], parsedEvent[1], parsedEvent[2]);
                        process(event, tasks);
                        break;
                    case "mark":
                        Task taskDone = getTask(split, tasks);
                        printLines();
                        taskDone.markAsDone();
                        printLines();
                        break;
                    case "unmark":
                        Task taskUndone = getTask(split, tasks);
                        printLines();
                        taskUndone.markAsUndone();
                        printLines();
                        break;
                    case "list":
                        printTasks(tasks);
                        break;
                    default:
                        throw new InvalidCommand("So that does not exist...You need to check what you are saying...");
                }
            } catch (InvalidCommand e) {
                printErrors(e);
            }
        }


        exit();
    }
}
