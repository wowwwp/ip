public class MarkCommand extends Command{
    private final int id;

    public MarkCommand(int id) {
        this.id = id;
    }
    @Override
    void execute(Storage storage, TaskList taskList) {
        if (taskList.checkTask(id)) {
            Task task = taskList.getTask(id);
            task.markAsDone();
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
