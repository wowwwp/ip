package Ella.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.*;

import Ella.task.Task;
import Ella.task.ToDo;
import Ella.task.Deadline;
import Ella.task.Event;

/**
 * Encapsulates all functionalities needed for loading and saving tasks in a JSON file.
 */
public class Storage {
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_NAME = "tasks.json";

    /**
     * Loads in the data present in the JSON file. Each task is represented as a JsonObject. Based on
     * the task name, an appropriate task is created and added into an {@link ArrayList} of tasks.
     *
     * @return ArrayList containing all the tasks found in the JSON file
     * @throws FileNotFoundException If the JSON file is not present in the initial directory
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        Path pathToFile = Paths.get(USER_DIR, DIRECTORY_PATH, FILE_NAME);
        if (!Files.exists(pathToFile)) {
            throw new FileNotFoundException("Uhh the data file does not exist");
        }

        ArrayList<Task> tasks = new ArrayList<>();
        Gson gson = new Gson();
        FileReader fileReader = new FileReader(pathToFile.toFile());
        JsonArray jsonArray = gson.fromJson(fileReader, JsonArray.class);

        for (JsonElement jsonObject: jsonArray) {
            JsonObject jsonTask = jsonObject.getAsJsonObject();
            String taskType = jsonTask.get("type").getAsString();
            switch (taskType) {
                case "ToDo":
                    ToDo toDo = new ToDo(jsonTask.get("name").getAsString(), jsonTask.get("completed").getAsBoolean());
                    tasks.add(toDo);
                    break;
                case "Deadline":
                    String deadlineString = jsonTask.get("dates").getAsJsonArray().get(0).getAsString();
                    LocalDateTime date = LocalDateTime.parse(deadlineString);
                    Deadline deadline = new Deadline(jsonTask.get("name").getAsString(), jsonTask.get("completed").getAsBoolean(), date);
                    tasks.add(deadline);
                    break;
                case "Event":
                    JsonArray dates = jsonTask.get("dates").getAsJsonArray();
                    String fromString = dates.get(0).getAsString();
                    LocalDateTime from = LocalDateTime.parse(fromString);
                    String toString = dates.get(1).getAsString();
                    LocalDateTime to = LocalDateTime.parse(toString);
                    Event event = new Event(jsonTask.get("name").getAsString(), jsonTask.get("completed").getAsBoolean(), from, to);
                    tasks.add(event);
                    break;
            }


        }

        return tasks;
    }

    /**
     * Converts {@link Task} to a JsonObject. Each JsonObject has properties which
     * can represent the various attributes of the {@link Task}.
     *
     * @param task Task which needs to be converted to a JsonObject
     * @return A JsonObject which represents the {@link Task}
     */
    public JsonObject taskToJson(Task task) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", task.getClass().getSimpleName());
        jsonObject.addProperty("completed", task.isDone);
        jsonObject.addProperty("name", task.description);
        LocalDateTime[] dates = task.getDates();
        JsonArray jsonArray = new JsonArray();
        for (LocalDateTime date : dates) {
            String dateString = date.toString();
            jsonArray.add(dateString);
        }
        jsonObject.add("dates", jsonArray);
        return jsonObject;
    }


    /**
     * Saves the {@link TaskList} into a JSON file. It creates a directory and a JSON file if not present and saves
     * all the tasks in a JsonArray. Each {@link Task} is saved as a JsonObject before being added to the JsonArray.
     *
     * @param tasks {@link TaskList} containing the current tasks
     * @throws IOException If there is issues saving the file
     */
    public void updateTasks(TaskList tasks) throws IOException {
        // Create data directory if it doesn't exist
        Path pathToDirectory = Paths.get(USER_DIR, DIRECTORY_PATH);
        if (!Files.exists(pathToDirectory)) {
            Files.createDirectory(pathToDirectory);
        }
        // Create a JSON file if it doesn't exist
        Path pathToFile = Paths.get(USER_DIR, DIRECTORY_PATH, FILE_NAME);

        if (!Files.exists(pathToFile)) {
            Files.createFile(pathToFile);
        }

        ArrayList<Task> tasksArray = tasks.getAllTasks();
        JsonArray taskArray = new JsonArray();
        for (Task task : tasksArray) {
            taskArray.add(taskToJson(task));
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        FileWriter fileWriter = new FileWriter(pathToFile.toFile());
        gson.toJson(taskArray, fileWriter);
        fileWriter.close();
    }
}
