package chloe;

// For collections
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

// For chloe task management
import chloe.tasktypes.Task;
import chloe.tasktypes.Deadline;
import chloe.tasktypes.Event;

// For file management
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Storage {
    private static final String DIRECTORY;
    private static final String FILE_NAME;
    private static final String TASK_SPLITTER = " # ";

    // Set up the variables needed for the file operations
    static {
        // Use the working directory to ensure JAR compatibility
        File baseDir = new File(System.getProperty("user.dir"), "data");
        DIRECTORY = baseDir.getAbsolutePath();
        FILE_NAME = new File(DIRECTORY, "chloeTasks.txt").getAbsolutePath();
    }

     /**
     * Loads the saved tasks from chloeTasks.txt.
     * Checks for the existence of the text file and
     * creates it if not found.
     */
    public static void loadSavedTasks(TaskList taskList) {
        File directory = new File(DIRECTORY);
        File file = new File(FILE_NAME);

        // Ensure the directory exists
        if (!directory.exists() && !directory.mkdirs()) {
            System.out.println("Failed to create directory: " + DIRECTORY);
            return;
        }

        // Ensure the file exists
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("No saved tasks found. New file created at: " + FILE_NAME);
                }
            } catch (IOException e) {
                System.out.println("Error creating tasks file: " + e.getMessage());
            }
        }

        // Load tasks from file
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseTaskLine(line, taskList);
            }
            System.out.println("Tasks loaded successfully from: " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + FILE_NAME);
            System.out.println("Error details: " + e.getMessage());
        }
    }

    private static void parseTaskLine(String line, TaskList taskList) {
        String[] parts = line.split(TASK_SPLITTER);
        if (parts.length < 3) return;

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        Task task;
        if (type.equals("T")) {
            task = new Task(description);
        } else if (type.equals("D") && parts.length == 4) {
            LocalDateTime dueDate = LocalDateTime.parse(parts[3], inputFormatter);
            task = new Deadline(description, dueDate);
        } else if (type.equals("E") && parts.length == 5) {
            LocalDateTime startDate = LocalDateTime.parse(parts[3], inputFormatter);
            LocalDateTime endDate = LocalDateTime.parse(parts[4], inputFormatter);
            task = new Event(description, startDate, endDate);
        } else {
            System.out.println("Unknown task type encountered: " + type);
            System.out.println("Skipping line...");
            return;
        }

        task.updateStatus(isDone);
        taskList.addTask(task);
    }

    /**
     * Updates the save file with the new task list.
     * Rewrites the file each time. Maybe will optimise
     * this at some point when I have time :""
     */
    public static void updateSaveFile(List<Task> taskList) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : taskList) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }   
}
