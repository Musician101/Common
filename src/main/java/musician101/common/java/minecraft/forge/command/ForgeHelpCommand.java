package musician101.common.java.minecraft.forge.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

public class ForgeHelpCommand extends AbstractForgeCommand
{
    private final AbstractForgeCommand mainCommand;

    public ForgeHelpCommand(AbstractForgeCommand mainCommand)
    {
        super("help", "Display help for " + EnumChatFormatting.getTextWithoutFormattingCodes(mainCommand.getCommandUsage(null)), mainCommand.getCommandUsage(null) + "help", 0, false);
        this.mainCommand = mainCommand;
    }

    @Override
    public void execute(ICommandSender sender, String[] args)
    {
        sender.addChatMessage(mainCommand.getCommandHelpInfo());
        mainCommand.getSubCommands().forEach(command -> sender.addChatMessage(command.getCommandHelpInfo()));
    }
}