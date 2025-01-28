package Ella.command;

import Ella.utils.Storage;
import Ella.utils.TaskList;

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
