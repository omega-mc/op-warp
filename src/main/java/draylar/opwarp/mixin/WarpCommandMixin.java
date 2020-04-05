package draylar.opwarp.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.WarpCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WarpCommand.class)
public abstract class WarpCommandMixin {

    @Shadow private static int execute(ServerCommandSource source, String taret) throws CommandSyntaxException {
        return 0;
    }

    /**
     * @author vini2003
     */
    @Overwrite
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("warp")
                        .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                        .then(CommandManager.argument("target", StringArgumentType.greedyString())
                                .executes((commandContext) ->
                                        execute(commandContext.getSource(), StringArgumentType.getString(commandContext, "target"))
                                )
                        )
        );
    }
}
