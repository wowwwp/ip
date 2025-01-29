package Ella.errors;

/**
 * Represents a custom exception thrown when the user input cannot
 * be parsed properly.
 * <p>This includes situations like incorrect number of arguments
 * to a command, commands which does not exist and invalid arguments
 * to commands.</p>
 */
public class InvalidCommand extends RuntimeException {
    public InvalidCommand(String message) {
        super(message);
    }
}
