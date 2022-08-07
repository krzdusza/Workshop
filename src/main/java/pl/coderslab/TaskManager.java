package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {

        tasks();
        viewOptions();
        selectOption();
    }

    public static void viewOptions() {
        System.out.println("\033[0;34m" + "Please select an option:" + "\033[0m");
        String[] options = {"add", "remove", "list", "exit"};
        for (String option : options) {
            System.out.println(option);
        }
        System.out.println();
    }

    public static int fileLinesCounter() {
        File file = new File("tasks.csv");
        try (Scanner scanner = new Scanner(file)) {
            int lineCounter1 = 0;
            while (scanner.hasNextLine()) {
                lineCounter1++;
                String line = scanner.nextLine();
            }
            return lineCounter1;
        } catch (FileNotFoundException ex) {
            System.err.println("Błąd odczytu pliku: " + ex.getLocalizedMessage());
        }
        return -1;
    }

    public static int fileValuesCounter() {
        File file = new File("tasks.csv");
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                return values.length;
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Błąd odczytu pliku: " + ex.getLocalizedMessage());
        }
        return -1;
    }

    public static String[][] tasks() {
        File file = new File("tasks.csv");
        fileLinesCounter();
        try (Scanner scanner = new Scanner(file)) {
            int i = 0;
            String[][] tasks = new String[fileLinesCounter()][fileValuesCounter()];
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                for (int j = 0; j < values.length; j++) {
                    tasks[i][j] = values[j];
                }
                i++;
            }
            return tasks;
        } catch (FileNotFoundException ex) {
            System.err.println("Błąd odczytu pliku: " + ex.getLocalizedMessage());
        }
        return null;
    }

    public static void selectOption() {
        Scanner scanner = new Scanner(System.in);
        String selectOpt = scanner.nextLine();

        switch (selectOpt) {
            case "add":
                System.out.println("add");
                break;
            case "remove":
                System.out.println("remove");
                break;
            case "list":
                System.out.println("list");
                listTask();
                break;
            case "exit":
                System.out.println("exit");
                exitTask();
                break;
            default:
                System.out.println("\033[1;91m" + "Incorrect input. Please select a correct option." + "\033[0m");
                break;
        }
    }

    public static void listTask() {
        tasks();
        for (int i = 0; i < tasks().length; i++) {
            System.out.print("\033[1;35m" + i + " : " + "\033[0m");
            for (int j = 0; j < tasks()[i].length; j++) {
                System.out.print(tasks()[i][j]);
            }
            System.out.println();
        }
    }

    public static void exitTask() {
        System.out.print("\033[0;31m" + " Bye, bye. " + "\033[0m");
    }
}