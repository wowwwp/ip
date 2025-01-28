public class Ui {

    public void printLines() {
        int CONSOLE_WIDTH = 80;
        String line = "-".repeat(CONSOLE_WIDTH);
        System.out.println(line);
    }

    public void greet() {
        printLines();
        System.out.println("Hi! This is Ella\nWhat do you need? I've got this.");
        printLines();
    }

    public void exit() {
        printLines();
        System.out.println("Bye... I know you will come back soon!");
        printLines();
    }

    public void printErrors(Exception e) {
        printLines();
        System.out.println(e.getMessage());
        printLines();
    }


    public void printErrors(String message) {
        printLines();
        System.out.println(message);
        printLines();
    }


}
