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

    public static void main(String[] args) {
        printLines();
        greet();
        printLines();

        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            String line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            printLines();
            System.out.println(line);
            printLines();
        }

        printLines();
        exit();
        printLines();
    }
}
