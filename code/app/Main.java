
package app;
import java.util.Scanner;
import service.TaskDao;
import model.Task;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static TaskDao taskDao = new TaskDao();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        do {
            showMenu();
            System.out.print("Choose an option: ");
            int choice = readInt();
            switch(choice){
                case 1 -> listTasks();
                case 2 -> addTask();
                case 3 -> updateTask();
                case 4 -> deleteTask();
                case 5 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
           
        } while (true);

    }

    private static void listTasks(){
        List<Task> tasks = taskDao.list();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ": " + tasks.get(i));
        }
    }

    private static void addTask(){
        System.out.print("Enter task name: ");
        String name = scanner.nextLine().trim();
        Task task = new Task(name);
        taskDao.add(task);
        System.out.println("Task added.");
    }

    private static void updateTask(){
        if(taskDao.list().isEmpty()){
            System.out.println("No tasks to update.");
            return;
        }
        listTasks();
        System.out.print("Enter task ID to update: ");
        int id = readInt();
        if(findById(id) == null){
            return;
        }
        System.out.print("Enter new task name: ");
        String name = scanner.nextLine().trim();
        Task task = new Task(name);
        taskDao.update(id, task);
        System.out.println("Task updated.");
    }

    private static void deleteTask(){
        if(taskDao.list().isEmpty()){
            System.out.println("No tasks to delete.");
            return;
        }
        listTasks();
        System.out.print("Enter task ID to delete: ");
        int id = readInt();
        if(findById(id) == null){
            return;
        }
        taskDao.delete(id);
        System.out.println("Task deleted.");
    }

    private static Task findById(int id){
        try {
            return taskDao.findById(id);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task not found.");
            return null;
        }
    }

    private static int readInt(){
        while(true){
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
            }
        }
    }

    private static void showMenu(){
        String menu = """
                1. List
                2. Add
                3. Update
                4. Delete
                5. Exit
                """;
        System.out.println(menu);
    }



}