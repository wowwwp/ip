public class ToDoCommand extends Command{
    private final String description;

    public ToDoCommand(String description) {
        this.description = description;
    }
    @Override
    void execute(Storage storage, TaskList taskList) {
        ToDo toDo = new ToDo(description);
        taskList.process(toDo);
//        storage.updateTasks(taskList.getTasks());
    }

    @Override
    boolean isExit() {
        return false;
    }
}
