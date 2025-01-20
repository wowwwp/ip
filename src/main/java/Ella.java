public class Ella {

    public static void printLines() {
        int CONSOLE_WIDTH = 80;
        String line = "─".repeat(CONSOLE_WIDTH);
        System.out.println(line);
    }

    public static void greet() {
        System.out.println("Hello! I'm Ella\nWhat can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        printLines();
        greet();
        printLines();
        exit();
        printLines();
    }
}
