package Ella.command;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Ella.utils.Storage;
import Ella.utils.TaskList;

public abstract class Command {
    public abstract void execute(Storage storage, TaskList taskList) throws IOException;

    public abstract boolean isExit();

    public static LocalDateTime parseTime(String time) {
        time = time.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime date = LocalDateTime.parse(time, formatter);
        LocalDateTime MAX_DATE = LocalDateTime.of(3035, 12, 31, 23, 59);
        if (date.isAfter(MAX_DATE)) {
            throw new DateTimeException("That is way too far ahead come on....");
        }
        return date;
    }

}
