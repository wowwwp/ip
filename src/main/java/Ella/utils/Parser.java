package Ella.utils;

import Ella.errors.InvalidCommand;
import Ella.command.*;

public class Parser {

    public static void checkBlank(String[] split, String message) throws InvalidCommand {
        for (String arg : split) {
            if (arg.isBlank()) {
                throw new InvalidCommand(message);
            }
        }
    }

    public static void checkInputFormat(String[] split, int expectedArguments, String message) throws InvalidCommand {
        if (split.length != expectedArguments) {
            throw new InvalidCommand(message);
        }
        checkBlank(split, message);
    }

    public static String parseToDo(String[] splits) throws IndexOutOfBoundsException {
        checkInputFormat(splits, 2, "You need to have a task for todo!!");
        return splits[1];
    }


    public static String[] parseDeadline(String[] splits) throws IndexOutOfBoundsException {
        // Check if its empty after deadline
        checkInputFormat(splits, 2, "Uhh you need to have a task and a date after the /by field...");
        // Check if input format for deadline is correct
        String[] splitsDeadline = splits[1].split("/by");
        checkInputFormat(splitsDeadline, 2, "Uhh you don't have a task or a date after the /by field...");
        return splitsDeadline;
    }

    public static Integer getTaskId(String[] splits) throws InvalidCommand {
        // Check if task number is present
        checkInputFormat(splits, 2, "You need to give me a valid task number...");
        // Check if task number is empty
        checkInputFormat(splits, 2, "You need to give me a valid task number...");
        // Parse integer
        int id = Integer.parseInt(splits[1]);
        return id - 1;
    }


    public static String[] parseEvent(String[] splits) {
        // Check if its empty after event
        checkInputFormat(splits, 2, "Uhh you need to have a task, a date after the /from field and a date after the /to field...");
        // Check if there is task and a date after /from
        String[] splitOne = splits[1].split("/from");
        checkInputFormat(splitOne, 2, "Uhh you don't have a task or a date after the /from field...");
        // Check if there is /from and /to
        String[] splitTwo = splitOne[1].split("/to");
        checkInputFormat(splitTwo, 2, "Uhh you don't have a date after the /from field or the /to field...");
        return new String[]{splitOne[0], splitTwo[0], splitTwo[1]};
    }

    public static String parseFind(String[] splits) {
        checkInputFormat(splits, 2, "You need to give me something to find...");
        return splits[1];
    }

    public static Command parse(String line) throws InvalidCommand {
        line = line.trim();
        String[] split = line.split(" ", 2);
        String command = split[0];

        if (command.equals("bye")) {
            return new ByeCommand();
        }

        switch (command) {
        case "todo":
            String description = parseToDo(split);
            return new ToDoCommand(description);
        case "deadline":
            String[] splitDeadline = parseDeadline(split);
            return new DeadlineCommand(splitDeadline);
        case "event":
            String[] splitEvent = parseEvent(split);
            return new EventCommand(splitEvent);
        case "mark":
            return new MarkCommand(getTaskId(split));
        case "unmark":
            return new UnMarkCommand(getTaskId(split));
        case "delete":
            return new DeleteCommand(getTaskId(split));
        case "list":
            return new ListCommand();
        case "arrange":
            return new ArrangeCommand();
        case "find":
            String keyword = parseFind(split);
            return new FindCommand(keyword);
        default:
            throw new InvalidCommand("So that does not exist...You need to check what you are saying...");
        }

    }
}
