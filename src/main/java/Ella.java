import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Ella {
    private static Ui ui = new Ui();
    
    public Ella () {
    }
    public static void process(Task task, ArrayList<Task> tasks) {
        tasks.add(task);
        ui.printLines();
        System.out.println("Ok, I will add this in...");
        System.out.printf("%s%nYou have %d tasks in the list%n", task.toString(), tasks.size());
        ui.printLines();
    }

    public static void printTasks(ArrayList<Task> tasks) {
        ui.printLines();
        System.out.println("This is what you got:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, tasks.get(i));
        }
        ui.printLines();

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

    public static LocalDateTime parseTime(String time) {
        time = time.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime date = LocalDateTime.parse(time, formatter);
        LocalDateTime MAX_DATE = LocalDateTime.of(3035, 12, 31, 23, 59);
        if (date.isAfter(MAX_DATE)) {
            throw new DateTimeException("That is way too far ahead come on....");
        }
        return date;
    }
    public static String[] parseDeadline(String[] splits) throws IndexOutOfBoundsException{
        // Check if its empty after deadline
        checkValidity(splits, "Uhh you need to have a task and a date after the /by field...");
        // Check if input format for deadline is correct
        String[] splitsDeadline = splits[1].split("/by");
        checkInputFormat(splitsDeadline, 2, "Uhh you don't have a task or a date after the /by field...");
        return splitsDeadline;
    }

    public static Deadline createDeadline(String[] splits) {
        String[] splitsDeadline = parseDeadline(splits);
        LocalDateTime by = parseTime(splitsDeadline[1]);
        return new Deadline(splitsDeadline[0], by);
    }

    public static Event createEvent(String[] splits) {
        String[] splitsEvent = parseEvent(splits);
        LocalDateTime from = parseTime(splitsEvent[1]);
        LocalDateTime to = parseTime(splitsEvent[2]);
        return new Event(splitsEvent[0], from, to);
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


    public static void deleteTask(Task task, ArrayList<Task> tasks) {
        ui.printLines();
        tasks.remove(task);
        System.out.printf("Ok got ya...I will remove this from the list...%n%s%nYou have %d tasks left%n", task.toString(), tasks.size());
        ui.printLines();
    }

    public static void arrangeTasks(ArrayList<Task> tasks) {
        List<Task> sortedTasks = tasks.stream()
                .filter(task -> !task.isDone)
                .sorted(new TaskComparator())
                .collect(Collectors.toList());
        ui.printLines();
        System.out.println("Ok here are your tasks arranged by deadline");
        for (Task task : sortedTasks) {
            System.out.println(task);
        }
        ui.printLines();

    }

    public static void main(String[] args) {
        ui.greet();

        // Initialize array to store tasks
        Storage storage = new Storage();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks = storage.loadTasks();
        } catch (FileNotFoundException e) {
            ui.printErrors(e);
        } catch (IndexOutOfBoundsException e) {
            ui.printErrors("Erm there has been issues with the loading the tasks...Did you do something..");
        } catch (DateTimeParseException e) {
            ui.printErrors("So the dates were not stored correctly in your file..We have to start over.....");
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
                        Deadline deadline = createDeadline(split);
                        process(deadline, tasks);
                        break;
                    case "event":
                        Event event = createEvent(split);
                        process(event, tasks);
                        break;
                    case "mark":
                        Task taskDone = getTask(split, tasks);
                        ui.printLines();
                        taskDone.markAsDone();
                        ui.printLines();
                        break;
                    case "unmark":
                        Task taskUndone = getTask(split, tasks);
                        ui.printLines();
                        taskUndone.markAsUndone();
                        ui.printLines();
                        break;
                    case "delete":
                        Task taskDeleted = getTask(split, tasks);
                        deleteTask(taskDeleted, tasks);
                        break;
                    case "list":
                        printTasks(tasks);
                        break;
                    case "arrange":
                        arrangeTasks(tasks);
                        break;
                    default:
                        throw new InvalidCommand("So that does not exist...You need to check what you are saying...");
                }
            } catch (InvalidCommand e) {
                ui.printErrors(e);
            } catch (NumberFormatException e) {
                ui.printErrors("You need to give me a valid task number...");
            } catch (DateTimeParseException e) {
                ui.printErrors("You got to follow the format to enter dates it is like dd/mm/yyyy Hhmm");
            } catch (DateTimeException e) {
                ui.printErrors(e);
            }
        }

        try {
            storage.updateTasks(tasks);
        } catch (IOException e) {
            ui.printErrors("There has been some error saving your file :( Your tasks are not saved..");
        }
        ui.exit();
    }
}
