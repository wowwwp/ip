package Ella;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

import Ella.command.Command;
import Ella.errors.InvalidCommand;
import Ella.utils.Parser;
import Ella.utils.Storage;
import Ella.utils.TaskList;
import Ella.utils.Ui;

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

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.printLines();
            ui.printErrors(e);
        } catch (IndexOutOfBoundsException e) {
            ui.printLines();
            ui.printErrors("Erm there has been issues with the loading the tasks...Did you do something..");
        } catch (DateTimeParseException e) {
            ui.printLines();
            ui.printErrors("So the dates were not stored correctly in your file..We have to start over.....");
        }
    }

    /**
     * Main function for Ella which handles all user input and errors encountered.
     */
    public void run() {
        ui.greet();

        boolean isExit = false;
        while(!isExit) {
            try {
                String line = ui.readCommand();
                ui.printLines();
                Command c = Parser.parse(line);
                isExit = c.isExit();
                c.execute(storage, tasks);
                ui.printLines();
            } catch (NumberFormatException e) {
                ui.printErrors("You need to give me a valid task number...");
            } catch (DateTimeParseException e) {
                ui.printErrors("You got to follow the format to enter dates it is like dd/mm/yyyy Hhmm");
            } catch (InvalidCommand | DateTimeException e) {
                ui.printErrors(e);
            } catch (IOException e) {
                ui.printErrors("There has been some error saving your file :( Your tasks are not saved..");
                ui.printLines();
            }
        }


    }

    public static void main(String[] args) {
        new Ella().run();
    }
}
