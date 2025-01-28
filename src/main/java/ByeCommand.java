public class ByeCommand extends Command {

    @Override
    void execute(Storage s, TaskList taskList) {
        System.out.println("Bye... I know you will come back soon!");
    }

    @Override
    boolean isExit() {
        return true;
    }
}
