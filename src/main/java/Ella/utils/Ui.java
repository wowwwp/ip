package Ella.utils;

import java.util.Scanner;

import Ella.Ella;

/**
 * Encapsulates all functionalities needed for user interaction
 */
public class Ui {
    private Scanner scan;


    public static String greet() {
        return "Hi! This is Ella\nWhat do you need? I've got this.";
    }

    public String showErrors(Exception e) {
        return e.getMessage();
    }


    public String showErrors(String message) {
        return message;
    }



}
