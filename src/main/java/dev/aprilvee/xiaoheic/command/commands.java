package dev.aprilvee.xiaoheic.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.network.Messages;
import dev.aprilvee.xiaoheic.network.packet.MaxQiS2C;
import dev.aprilvee.xiaoheic.network.packet.QiSyncS2C;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

public class commands {


    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("setqi")
                .then(Commands.argument("amount", IntegerArgumentType.integer())
                .executes(context -> setqi(context.getSource().getPlayerOrException(), IntegerArgumentType.getInteger(context, "amount")))
        ));
        dispatcher.register(Commands.literal("setmaxqi")
                .then(Commands.argument("amount", IntegerArgumentType.integer())
                        .executes(context -> setmaxqi(context.getSource().getPlayerOrException(), IntegerArgumentType.getInteger(context, "amount")))
                ));
    }

    public static int setqi(ServerPlayer player, int input){
        SpiritCap qi = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        qi.setQi(input);
        Messages.sendToClient(new QiSyncS2C(qi.getQi()), player);
        return 1;
    }
    public static int setmaxqi(ServerPlayer player, int input){
        SpiritCap qi = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        qi.setMaxqi(input);
        Messages.sendToClient(new MaxQiS2C(qi.getMaxqi()), player);
        return 1;
    }
}
