package ella.command;

import ella.utils.Storage;
import ella.utils.TaskList;

/**
 * Represents the bye command, which prints a goodbye message
 * to the user. After this command is used, the user will
 * exit the chatbot.
 */
public class ByeCommand extends Command {

    @Override
    public String execute(Storage s, TaskList taskList) {
        return "Bye... I know you will come back soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
