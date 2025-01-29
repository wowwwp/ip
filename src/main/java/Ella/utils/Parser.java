package Ella.utils;

import Ella.errors.InvalidCommand;
import Ella.command.*;

/**
 * Represents a Parser which can parse user input and returns the relevant commands.
 *
 * <p> It maps the user input to one of the relevant commands if the user input does
 * not match any of the relevant commands an {@link InvalidCommand} is thrown.</p>
 */
public class Parser {
    /**
     * Checks if any of the elements in the array is blank.
     *
     * @param split Array containing the user input which is split
     * @param message Error message if there is failure
     * @throws InvalidCommand If the split contains whitespace
     */
    private static void checkBlank(String[] split, String message) throws InvalidCommand {
        for (String arg: split) {
            if (arg.isBlank()) {
                throw new InvalidCommand(message);
            }
        }
    }

    /**
     * Checks if the {@code split} array contains the expected number of arguments,
     * and verifies that none of the elements are blank.
     *
     * @param split An array containing user input which is split
     * @param expectedArguments Number of elements which should be present after split
     * @param message Error message
     * @throws InvalidCommand If the {@code split} does not contain the expected number of elements
     */
    public static void checkInputFormat(String[] split, int expectedArguments,String message) throws InvalidCommand {
        if (split.length != expectedArguments) {
            throw new InvalidCommand(message);
        }
        checkBlank(split, message);
    }

    /**
     * Checks if the user input has the description of the task
     *
     * @param splits An array containing user input which is split.
     * @return Description of the task.
     * @throws IndexOutOfBoundsException If the user did not provide a task description.
     */
    public static String parseToDo(String[] splits) throws IndexOutOfBoundsException{
        checkInputFormat(splits, 2, "You need to have a task for todo!!");
        return splits[1];
    }

    /**
     * Checks if the user input has the task description and a date after the /by field.
     *
     * @param splits An array containing the user input which is split.
     * @return An array containing the task description and a date.
     * @throws IndexOutOfBoundsException If the user did not provide a task and a date.
     */
    public static String[] parseDeadline(String[] splits) throws IndexOutOfBoundsException{
        // Check if its empty after deadline
        checkInputFormat(splits, 2, "Uhh you need to have a task and a date after the /by field...");
        // Check if input format for deadline is correct
        String[] splitsDeadline = splits[1].split("/by");
        checkInputFormat(splitsDeadline, 2, "Uhh you don't have a task or a date after the /by field...");
        return splitsDeadline;
    }

    /**
     * Checks if the user input has the task description, a date after the /from field and
     * a date after the /to field.
     *
     * @param splits An Array containing the user input which is split
     * @return An array containing task description, a date representing when the event starts and
     * a date representing when the event ends.
     * @throws IndexOutOfBoundsException If the user input does not have a task, date after the from /field or
     * a date after the /to field.
     */
    public static String[] parseEvent(String[] splits) throws IndexOutOfBoundsException{
        // Check if its empty after event
        checkInputFormat(splits, 2, "Uhh you need to have a task, a date after the /from field and a date after the /to field...");
        // Check if there is task and a date after /from
        String[] splitOne = splits[1].split("/from");
        checkInputFormat(splitOne, 2,"Uhh you don't have a task or a date after the /from field...");
        // Check if there is /from and /to
        String[] splitTwo = splitOne[1].split("/to");
        checkInputFormat(splitTwo, 2, "Uhh you don't have a date after the /from field or the /to field...");
        return new String[]{splitOne[0], splitTwo[0], splitTwo[1]};
    }

    /**
     * Checks if there is an integer given as a task number.
     *
     * @param splits An array containing the user input which is split
     * @return Index of a task
     * @throws InvalidCommand If there is no integer present o
     */
    public static Integer getTaskId(String[] splits) throws InvalidCommand{
        // Check if task number is present
        checkInputFormat(splits, 2, "You need to give me a valid task number...");
        // Parse integer
        int id = Integer.parseInt(splits[1]);
        return id - 1;
    }

    /**
     * Splits the user input and parses it based on the command. If the user input follows the input structure of a
     * command,a new {@link Command} is returned which encapsulates the functionality of the command.
     *
     * @param line Raw user input
     * @return Command based on the user input
     * @throws InvalidCommand If the user gives a command which does not exist or does not follow the input structure
     * of the command.
     */
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
            default:
                throw new InvalidCommand("So that does not exist...You need to check what you are saying...");
        }

    }
}
