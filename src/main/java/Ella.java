import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Ella {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

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
            } catch (InvalidCommand e) {
                ui.printErrors(e);
            } catch (NumberFormatException e) {
                ui.printErrors("You need to give me a valid task number...");
            } catch (DateTimeParseException e) {
                ui.printErrors("You got to follow the format to enter dates it is like dd/mm/yyyy Hhmm");
            } catch (DateTimeException e) {
                ui.printErrors(e);
            }
        }

        try {
            storage.updateTasks(tasks);
        } catch (IOException e) {
            ui.printErrors("There has been some error saving your file :( Your tasks are not saved..");
            ui.printLines();
        }
    }

    public static void main(String[] args) {
        new Ella().run();
    }
}
