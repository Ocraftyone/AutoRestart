package de.geheimagentnr1.auto_restart.elements.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import de.geheimagentnr1.auto_restart.AutoRestart;
import de.geheimagentnr1.auto_restart.task.AutoRestartTask;
import de.geheimagentnr1.auto_restart.util.ServerRestarter;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;


@SuppressWarnings("SameReturnValue")
public class RestartCommand {


    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("restart")
                .requires(source -> source.hasPermission(4))
                .executes(RestartCommand::restart));

        dispatcher.register(Commands.literal("startEmptyTimer")
                .requires(source -> source.hasPermission(4))
                .executes(RestartCommand::startEmptyTimer));

        dispatcher.register(Commands.literal("stopEmptyTimer")
                .requires(source -> source.hasPermission(4))
                .executes(RestartCommand::stopEmptyTimer));
    }


    private static int restart(CommandContext<CommandSourceStack> context) {

        CommandSourceStack source = context.getSource();
        source.sendSuccess(new TextComponent("Restarting the server"), true);
        ServerRestarter.restart(source.getServer());
        return Command.SINGLE_SUCCESS;
    }

    private static int startEmptyTimer(CommandContext<CommandSourceStack> context) {
        AutoRestartTask.setEmptyTime();
        context.getSource().sendSuccess(new TextComponent("Server restart on empty timer started"), true);
        return Command.SINGLE_SUCCESS;
    }

    private static int stopEmptyTimer(CommandContext<CommandSourceStack> context) {
        AutoRestartTask.resetEmptyTime();
        context.getSource().sendSuccess(new TextComponent("Server restart on empty timer canceled"), true);
        return Command.SINGLE_SUCCESS;
    }
}
