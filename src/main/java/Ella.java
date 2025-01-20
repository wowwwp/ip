import java.util.ArrayList;
import java.util.Scanner;

public class Ella {

    public static void printLines() {
        int CONSOLE_WIDTH = 80;
        String line = "─".repeat(CONSOLE_WIDTH);
        System.out.println(line);
    }

    public static void greet() {
        System.out.println("Hi! This is Ella\nWhat do you need? I've got this.");
    }

    public static void exit() {
        System.out.println("Bye... I know you will come back soon!");
    }

    public static void process(String task, ArrayList<String> tasks) {
        tasks.add(task);
        System.out.printf("added: %s\n", task);
    }

    public static void printTasks(ArrayList<String> tasks) {
        String tasksString = "";

        for (int i = 0; i < tasks.size(); i++) {
            tasksString += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        System.out.print(tasksString);
    }

    public static void main(String[] args) {
        printLines();
        greet();
        printLines();

        ArrayList<String> tasks = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            String line = in.nextLine();

            if (line.equals("bye")) {
                break;
            }

            switch (line) {
                case "list":
                    printLines();
                    printTasks(tasks);
                    printLines();
                    break;
                default:
                    printLines();
                    process(line, tasks);
                    printLines();
            }
        }

        printLines();
        exit();
        printLines();
    }
}
