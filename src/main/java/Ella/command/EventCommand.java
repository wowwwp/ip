package ella.command;

import java.io.IOException;
import java.time.LocalDateTime;

import ella.utils.Storage;
import ella.utils.TaskList;
import ella.task.Event;

/**
 * Represents event command which is responsible for creates a
 * new {@link Event}. It updates the {@code taskList} with the new
 * {@link Event} and updates the JSON file.
 */
public class EventCommand extends Command {
    private final String[] args;

    /**
     * Initializes a {@link EventCommand}.
     *
     * @param args An array containing the description, from and to time.
     */
    public EventCommand(String[] args) {
        this.args = args;
    }

    /**
     * Initializes new {@link Event} object. It extracts the task description
     * and converts the time string into a {@link LocalDateTime} object,
     * which is used to create a {@link Event}.
     *
     * @return A {@link Event} which contains the task description, from time and to time.
     */
    protected Event createEvent() {
        LocalDateTime from = parseTime(args[1]);
        LocalDateTime to = parseTime(args[2]);
        return new Event(args[0], from, to);
    }

    /**
     * {@inheritDoc}
     *
     * <p>It creates a new {@link Event} and adds it to the taskList. The new
     * {@link Event} is saved into the JSON file.</p>
     *
     * @param storage Handles saving and loading tasks when taskList changes
     * @param taskList An ArrayList containing tasks
     * @throws IOException When {@code taskList} cannot be saved into JSON file
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws IOException {
        Event event = createEvent();
        String output = taskList.process(event);
        storage.updateTasks(taskList);
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
