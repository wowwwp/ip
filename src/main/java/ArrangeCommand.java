public class ArrangeCommand extends Command {

    @Override
    void execute(Storage storage, TaskList taskList) {
        taskList.arrangeTasks();
    }

    @Override
    boolean isExit() {
        return false;
    }
}
