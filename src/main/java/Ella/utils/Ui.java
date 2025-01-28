package Ella.utils;

import java.util.Scanner;

public class Ui {
    private Scanner scan;

    public Ui() {
        this.scan = new Scanner(System.in);
    }

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

    public void printErrors(Exception e) {
        System.out.println(e.getMessage());
        printLines();
    }


    public void printErrors(String message) {
        System.out.println(message);
        printLines();
    }

    public String readCommand() {
        return scan.nextLine();
    }


}
