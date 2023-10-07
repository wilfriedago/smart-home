package command.core;

/**
 * This is an interface for commands that have an undo action.
 * Example: Switch on > undo > Switch off
 * Every Command class should implement its own undo logic.
 */
public interface Undoable {
    Command undo();
}
