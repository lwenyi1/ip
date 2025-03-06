# Chloe User Guide

## Introduction
Chloe is an interactive task manager that helps you keep track of your tasks, deadlines, and events efficiently.

## Adding Todos

To add a **todo**, use the `todo` command followed by the task description.

### Example:

```
todo Buy groceries
```

### Expected Outcome:

```
**********************************************
Added: [ ][T] Buy groceries
**********************************************
```

## Adding Deadlines

To add a **deadline**, use the `deadline` command followed by the task description and the due date/time, separated by `/by`.

### Example:

```
deadline Submit assignment /by 20/02/2025 21:00
```

### Expected Outcome:

```
**********************************************
Added: [ ][D] Submit assignment (by: 20-02-2025 21:00)
**********************************************
```

## Adding Events

To add an **event**, use the `event` command followed by the event description, start time (`/from`), and end time (`/to`).

### Example:

```
event Team meeting /from 21/02/2025 14:00 /to 21/02/2025 16:00
```

### Expected Outcome:

```
**********************************************
Added: [ ][E] Team meeting (from: 21-02-2025 14:00 to: 21-02-2025 16:00)
**********************************************
```

## Marking Tasks as Done

To **mark** a task as done, use the `mark` command followed by the task number.

### Example:

```
mark 1
```

### Expected Outcome:

```
**********************************************
Marked "Buy groceries" as done!
[X][T] Buy groceries
**********************************************
```

## Unmarking Tasks

To **unmark** a task (mark it as not done), use the `unmark` command followed by the task number.

### Example:

```
unmark 1
```

### Expected Outcome:

```
**********************************************
Oh "Buy groceries" is not done? Unmarked.
[ ][T] Buy groceries
**********************************************
```

## Deleting Tasks

To **delete** a task, use the `delete` command followed by the task number.

### Example:

```
delete 1
```

### Expected Outcome:

```
**********************************************
Ok! Deleting this task:
[ ][T] Buy groceries
**********************************************
```

## Listing All Tasks

To **list** all tasks, use the `list` command.

### Example:

```
list
```

### Expected Outcome:

```
**********************************************
You have 3 tasks in your list:
1. [X][T] Buy groceries
2. [ ][D] Submit assignment (by: 20-02-2025 21:00)
3. [ ][E] Team meeting (from: 21-02-2025 14:00 to: 21-02-2025 16:00)
**********************************************
```

## Finding Tasks

To **find** tasks containing a specific keyword, use the `find` command followed by the keyword.

### Example:

```
find groceries
```

### Expected Outcome:

```
**********************************************
Here are the matching tasks in your list:
1. [X][T] Buy groceries
**********************************************
```

