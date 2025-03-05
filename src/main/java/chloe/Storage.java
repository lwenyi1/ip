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

/**
 * Handles file operations for chloe.
 */
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

    /**
     * Parses a line of text from the saved tasks file and adds the corresponding Task
     * object to the provided TaskList. The line is expected to be in a specific format
     * where the first part indicates the task type ("T" for Todo, "D" for Deadline, "E" for Event),
     * the second part indicates whether the task is done ("1" for done, "0" for not done), and
     * the remaining parts contain the task description and other task-specific details.
     * 
     * The method handles Task, Deadline, and Event types and updates the task's status
     * before adding it to the TaskList. If an unknown task type is encountered, the line is skipped.
     *
     * @param line      the line of text representing a task
     * @param taskList  the TaskList to which the parsed Task object will be added
     */

    private static void parseTaskLine(String line, TaskList taskList) {
        String[] parts = line.split(TASK_SPLITTER);
        if (parts.length < 3) return;

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
            System.out.println("Unknown task type encountered: " + type);
            System.out.println("Skipping line...");
            return;
        }

        task.updateStatus(isDone);
        taskList.addTask(task);
    }

    /**
     * Updates the saved tasks file with the current contents of the TaskList.
     * This method overwrites the existing file with the new tasks.
     * If any errors occur during the file operation, an error message is printed to the console.
     * @param taskList the TaskList containing the tasks to be saved to the file
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
