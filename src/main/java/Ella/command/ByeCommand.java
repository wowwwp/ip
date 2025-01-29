package Ella.command;

import Ella.utils.Storage;
import Ella.utils.TaskList;

/**
 * Represents the bye command, which prints a goodbye message
 * to the user. After this command is used, the user will
 * exit the chatbot.
 */
public class ByeCommand extends Command {

    @Override
    public void execute(Storage s, TaskList taskList) {
        System.out.println("Bye... I know you will come back soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
