package ella.command;

import java.io.IOException;
import java.time.LocalDateTime;

import ella.task.Deadline;
import ella.utils.Storage;
import ella.utils.TaskList;

/**
 * Represents deadline command which creates a new Deadline
 * and updates the JSON file.
 */
public class DeadlineCommand extends Command {
    private final String[] splits;

    /**
     * Creates an Instance of the {@link DeadlineCommand}.
     *
     * @param splits Array containing the task and time
     */
    public DeadlineCommand(String[] splits) {
        this.splits = splits;
    }

    /**
     * Initializes new {@link Deadline} object. It extracts the task description
     * and converts the time string into a {@link LocalDateTime} object,
     * which is used to create a {@link Deadline}
     *
     * @return A Deadline which contains the task description and time
     */
    protected Deadline createDeadline() {
        LocalDateTime by = parseTime(splits[1].trim());
        return new Deadline(splits[0], by);
    }

    /**
     * {@inheritDoc}
     *
     * <p>It creates a new {@link Deadline} and adds it to the taskList. The new
     * {@link Deadline} is saved into the JSON file.</p>
     *
     * @param storage Handles saving and loading tasks when taskList changes
     * @param taskList An ArrayList containing tasks
     * @throws IOException When {@code taskList} cannot be saved into JSON file
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws IOException {
        Deadline deadline = createDeadline();
        String output = taskList.process(deadline);
        storage.updateTasks(taskList);
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
