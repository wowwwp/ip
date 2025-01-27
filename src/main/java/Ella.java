import java.io.FileNotFoundException;
import java.io.IOException;
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
        if (split.length < 2) {
            throw new InvalidCommand(message);
        }
    }

    public static void checkInputFormat(String[] split, int expectedArguments,String message) throws InvalidCommand {
        if (split.length != expectedArguments) {
            throw new InvalidCommand(message);
        }
        for (String arg: split) {
            if (arg.isBlank()) {
                throw new InvalidCommand(message);
            }
        }
    }


    public static Task getTask(String[] splits, ArrayList<Task> tasks) throws InvalidCommand{
        // Check if task number is present
        checkValidity(splits, "You need to give me a valid task number...");
        // Check if task number is empty
        checkInputFormat(splits, 2, "You need to give me a valid task number...");
        // Parse integer
        int id = Integer.parseInt(splits[1]);
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
        // Check if its empty after deadline
        checkValidity(splits, "Uhh you need to have a task and a date after the /by field...");
        // Check if input format for deadline is correct
        String[] splitsDeadline = splits[1].split("/by");
        checkInputFormat(splitsDeadline, 2, "Uhh you don't have a task or a date after the /by field...");
        return splitsDeadline;
    }

    public static String[] parseEvent(String[] splits) {
        // Check if its empty after event
        checkValidity(splits, "Uhh you need to have a task, a date after the /from field and a date after the /to field...");
        // Check if there is task and a date after /from
        String[] splitOne = splits[1].split("/from");
        checkInputFormat(splitOne, 2,"Uhh you don't have a task or a date after the /from field...");
        // Check if there is /from and /to
        String[] splitTwo = splitOne[1].split("/to");
        checkInputFormat(splitTwo, 2, "Uhh you don't have a date after the /from field or the /to field...");
        return new String[]{splitOne[0], splitTwo[0], splitTwo[1]};
    }

    public static void printErrors(Exception e) {
        printLines();
        System.out.println(e.getMessage());
        printLines();
    }

    public static void deleteTask(Task task, ArrayList<Task> tasks) {
        printLines();
        tasks.remove(task);
        System.out.printf("Ok got ya...I will remove this from the list...%n%s%nYou have %d tasks left%n", task.toString(), tasks.size());
        printLines();
    }

    public static void main(String[] args) throws IOException {
        greet();

        // Initialize array to store tasks
        Storage storage = new Storage();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks = storage.loadTasks();
        } catch (FileNotFoundException e) {
            printErrors(e);
        } catch (IndexOutOfBoundsException e) {
            printErrors(new IndexOutOfBoundsException("Erm there has been issues with the loading the tasks...Did you do something.."));
        }

        Scanner in = new Scanner(System.in);

        while(in.hasNextLine()) {
            try {
                // parse command
                String line = in.nextLine();
                line = line.trim();
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
                    case "delete":
                        Task taskDeleted = getTask(split, tasks);
                        deleteTask(taskDeleted, tasks);
                        break;
                    case "list":
                        printTasks(tasks);
                        break;
                    default:
                        throw new InvalidCommand("So that does not exist...You need to check what you are saying...");
                }
            } catch (InvalidCommand e) {
                printErrors(e);
            } catch (NumberFormatException e) {
                printErrors(new NumberFormatException("You need to give me a valid task number..."));
            }
        }

        storage.updateTasks(tasks);
        exit();
    }
}
