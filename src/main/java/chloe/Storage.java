package chloe;

// For collections
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
    public static void loadSavedTasks(List<Task> taskList) {
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
                String[] parts = line.split(" # "); // Use # to split task components
                if (parts.length < 3) continue; // Skip lousy lines which are missing data

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task;
                if (type.equals("T")) {
                    task = new Task(description);
                } else if (type.equals("D") && parts.length == 4) {
                    task = new Deadline(description, parts[3]);
                } else if (type.equals("E") && parts.length == 5) {
                    task = new Event(description, parts[3], parts[4]);
                } else {
                    continue; // Skip lousy lines with wrong data
                }

                task.updateStatus(isDone); // Mark tasks which were previously marked
                taskList.add(task); // Add to task list
            }
            System.out.println("Tasks loaded successfully from: " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + FILE_NAME);
            System.out.println("Error details: " + e.getMessage());
        }
    }

    /**
     * Updates the save file with the new task list.
     * Rewrites the file each time. Maybe will optimise
     * this at some point when I have time :""
     */
    public void updateSaveFile(List<Task> taskList) {

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
