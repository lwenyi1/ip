# Chloe User Guide

## Introduction

Chloe is a simple interactive task manager that helps you keep track of your tasks, deadlines, and events efficiently.

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
deadline Submit assignment /by Monday 9pm
```

### Expected Outcome:

```
**********************************************
Added: [ ][D] Submit assignment (by: Monday 9pm)
**********************************************
```

## Adding Events

To add an **event**, use the `event` command followed by the event description, start time (`/from`), and end time (`/to`).

### Example:

```
event Team meeting /from Friday 2pm /to 4pm
```

### Expected Outcome:

```
**********************************************
Added: [ ][E] Team meeting (from: Friday 2pm to: 4pm)
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


