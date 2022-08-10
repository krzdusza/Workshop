package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import static pl.coderslab.colors.ConsoleColors.*;

public class TaskManager {
    public static void main(String[] args) {
        tasks();
        viewOptions();
        selectOption();
    }

    public static String[][] tasks = new String[0][];

    public static void viewOptions() {
        System.out.println("\n" + BLUE + "Please select an option:" + RESET);
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
                    tasks[i][j] = values[j].trim();
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
                addTask();
                viewOptions();
                selectOption();
                break;
            case "remove":
                System.out.println("remove");
                removeTask();
                viewOptions();
                selectOption();
                break;
            case "list":
                System.out.println("list");
                listTask();
                viewOptions();
                selectOption();
                break;
            case "exit":
                System.out.println("exit");
                exitTask();
                break;
            default:
                System.out.println(RED_BOLD_BRIGHT + "Incorrect input. Please select a correct option." + RESET);
                viewOptions();
                selectOption();
        }
    }

    public static void listTask() {
        tasks();
        for (int i = 0; i < tasks().length; i++) {
            System.out.print(PURPLE_BOLD + i + " : " + RESET);
            for (int j = 0; j < tasks()[i].length; j++) {
                System.out.print(tasks()[i][j] + ", ");
            }
            System.out.println();
        }
    }

    public static void addTask() {
        Scanner scanner = new Scanner(System.in);
        String newTask;
        String newDate;
        String newImportant;
        do {
            System.out.println("Please add task description");
            newTask = scanner.nextLine();
        } while (newTask.isEmpty());
        do {
            System.out.println("Please add task due date (format: YYYY-MM-DD)");
            newDate = scanner.nextLine();
        } while (!(newDate.startsWith("2") && newDate.startsWith("-", 4) && newDate.startsWith("-", 7)));
        do {
            System.out.println("Is your task is important: true/false");
            newImportant = scanner.nextLine();
        } while (!("true".equals(newImportant) || "false".equals(newImportant)));

        tasks = ArrayUtils.add(tasks(), new String[]{newTask, newDate, newImportant});

        try (PrintWriter printWriter = new PrintWriter("tasks.csv")) {
            for (int i = 0; i < tasks.length; i++) {
                for (int j = 0; j < tasks[i].length; j++) {
                    printWriter.print(tasks[i][j] + ",");
                }
                printWriter.println();
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Błąd zapisu do pliku: " + ex.getLocalizedMessage());
        }
        System.out.println();
        System.out.println(GREEN_BOLD + "Correct. Task was added." + RESET);
        System.out.println();

        tasks();
    }

    public static void removeTask() {
        Scanner scanner = new Scanner(System.in);
        String delTask;
        System.out.println("Please select number to remove");
        delTask = scanner.nextLine();
        System.out.println("Value was successfully deleted");


    }

    public static void exitTask() {
        System.out.print(RED + " Bye, bye. " + RESET);
    }
}