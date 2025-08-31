package space.ankanroychowdhury.commands;

import space.ankanroychowdhury.OutputPrinter;
import space.ankanroychowdhury.models.Command;
import space.ankanroychowdhury.services.UserService;

public abstract class UserCommandExecutor implements CommandExecutor {
    protected UserService userService;
    protected OutputPrinter outputPrinter;

    protected UserCommandExecutor(final UserService userService,
        final OutputPrinter outputPrinter) {
      this.userService = userService;
      this.outputPrinter = outputPrinter;
    }

    /**
     * Validates that whether a command is valid to be executed or not.
     *
     * @param command Command to be validated.
     * @return Boolean indicating whether command is valid or not.
     */
    public abstract boolean validate(Command command);

    /**
     * Executes the command.
     *
     * @param command Command to be executed.
     */
    public abstract void execute(Command command);
}
