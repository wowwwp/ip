public class ListCommand extends Command {

    @Override
    void execute(Storage storage, TaskList taskList) {
        taskList.printTasks();
    }

    @Override
    boolean isExit() {
        return false;
    }
}
