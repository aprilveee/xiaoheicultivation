package dev.aprilvee.xiaoheic.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.cultivation.EnvironmentQi;
import dev.aprilvee.xiaoheic.network.Messages;
import dev.aprilvee.xiaoheic.network.packet.MaxQiS2C;
import dev.aprilvee.xiaoheic.network.packet.QiSyncS2C;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Arrays;

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
        dispatcher.register(Commands.literal("xrun")
                .then(Commands.argument("function", StringArgumentType.string())
                        .executes(context -> xrun(context.getSource().getPlayerOrException(), StringArgumentType.getString(context, "function")))
                ));

        dispatcher.register(Commands.literal("xset")
                .then(Commands.argument("value", StringArgumentType.string())
                        .then(Commands.argument("amount", FloatArgumentType.floatArg())
                                .executes(context -> xset(context.getSource().getPlayerOrException(), StringArgumentType.getString(context, "value"),
                                        FloatArgumentType.getFloat(context, "amount")))
                        )));

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

    public static int xrun(ServerPlayer plaer, String input){
        switch(input){
            case "enviroqi":
                plaer.sendSystemMessage(Component.literal(Arrays.toString(EnvironmentQi.getEnviroQi(plaer.blockPosition(), plaer.level(), 8))));

                return 1;
            default:
                plaer.sendSystemMessage(Component.literal("Invalid input"));
                return 1;
        }
    }

    public static int xset(ServerPlayer player, String value, float in){
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        switch (value){
            case "qi": sp.setQi((int) in);Messages.sendToClient(new QiSyncS2C(sp.getQi()), player);return 1;
            case "maxqi": sp.setMaxqi((int) in);Messages.sendToClient(new MaxQiS2C(sp.getMaxqi()), player); return 1;

            case "maxqix": sp.setMaxqiX(in); return 1;
            case "qiregen": sp.setQiregen(in); return 1;
            case "spelldamage": sp.setSpelldamage(in); return 1;
            case "spellresist": sp.setSpellresist(in); return 1;
            case "spellcost": sp.setSpellcost(in); return 1;

            case "exp": sp.setCultivation(in); return 1;
            case "metal": sp.setMetal(in); return 1;
            case "water": sp.setWater(in); return 1;
            case "wood": sp.setWood(in); return 1;
            case "fire": sp.setFire(in); return 1;
            case "earth": sp.setEarth(in); return 1;

            case "elementlimit": sp.setElementlimit(in); return 1;
            default:
                player.sendSystemMessage(Component.literal("Invalid input"));
                return 1;

        }

    }
    public static int xset2(ServerPlayer player, String value, String input, int index){
        return 0;
    }
}
