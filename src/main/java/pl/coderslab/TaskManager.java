package pl.coderslab;

public class TaskManager {
    public static void main(String[] args) {

        //  selectOption();
        viewOptions();
    }

    public static void viewOptions() {
        System.out.println("\033[0;34m" + "Please select an option:" + "\033[0m");
        String[] options = {"add", "remove", "list", "exit"};
        for (String option : options) {
            System.out.println(option);
        }
    }

}