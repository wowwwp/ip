public class DeleteCommand extends Command {
    private int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    void execute(Storage storage, TaskList taskList) {
        if (taskList.checkTask(id)) {
            Task task = taskList.getTask(id);
            taskList.deleteTask(task);
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
