public class UnMarkCommand extends Command {
    private final int id;

    public UnMarkCommand(int id) {
        this.id = id;
    }

    @Override
    void execute(Storage storage, TaskList taskList) {
        if (taskList.checkTask(id)) {
            Task task = taskList.getTask(id);
            task.markAsUndone();
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
