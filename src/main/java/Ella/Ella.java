package ella;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

import ella.command.Command;
import ella.errors.InvalidCommand;
import ella.utils.Parser;
import ella.utils.Storage;
import ella.utils.TaskList;
import ella.utils.Ui;

/**
 * Main class responsible for initializing helper classes required to process
 * user input and generate an appropriate output.
 */
public class Ella {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Instantiates Ella. Instantiates a new {@link Ui}, {@link Storage} and {@link TaskList} for the session.
     * If there is an existing JSON file, tasks are loaded and used to populate {@link TaskList}
     */
    public Ella() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();

    }

    /**
     * Loads in tasks stored in the JSON file into {@code tasks}.
     *
     * @return String representing the status of the file loading process.
     */
    public String populateTasks() {
        try {
            tasks = new TaskList(storage.loadTasks());
            return "Nice! I loaded all your past tasks.";
        } catch (IOException e) {
            return ui.showErrors(e);
        } catch (IndexOutOfBoundsException e) {
            return ui.showErrors("Erm there has been issues with the loading the tasks...Did you do something..");
        } catch (DateTimeParseException e) {
            return ui.showErrors("So the dates were not stored correctly in your file..We have to start over.....");
        } catch (Exception e) {
            return ui.showErrors("Oops...there is some error loading the tasks...");
        }
    }

    /**
     * Main function for Ella which handles all user input and errors encountered.
     */
    public String run(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(storage, tasks);
        } catch (NumberFormatException e) {
            return ui.showErrors("You need to give me a valid task number...");
        } catch (DateTimeParseException e) {
            return ui.showErrors("You got to follow the right format for the dates...");
        } catch (InvalidCommand | DateTimeException e) {
            return ui.showErrors(e);
        } catch (IOException e) {
            return ui.showErrors("There has been some error saving your file :( Your tasks are not saved..");
        } catch (Exception e) {
            return ui.showErrors("Oops..there is some error processing that try something else");
        }

    }

}
