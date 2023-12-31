package dev.aprilvee.xiaoheic.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.cultivation.EnvironmentQi;
import dev.aprilvee.xiaoheic.data.Datalist;
import dev.aprilvee.xiaoheic.network.Messages;
import dev.aprilvee.xiaoheic.network.packet.MaxQiS2C;
import dev.aprilvee.xiaoheic.network.packet.QiSyncS2C;
import dev.aprilvee.xiaoheic.spell.ICastable;
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
                        .then(Commands.argument("input", IntegerArgumentType.integer())
                                .executes(context -> xrun(context.getSource().getPlayerOrException(), StringArgumentType.getString(context, "function"), IntegerArgumentType.getInteger(context, "input")))
                        )));
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
        qi.setMaxQi(input);
        Messages.sendToClient(new MaxQiS2C(qi.maxqi), player);
        return 1;
    }

    public static int xrun(ServerPlayer player, String function, int input){
        switch(function){
            case "rawenviroqi":
                player.sendSystemMessage(Component.literal(Arrays.toString(EnvironmentQi.getEnviroQi(player.blockPosition(), player.level(),input))));
                return 1;
            case "enviroqi":
                player.sendSystemMessage(Component.literal(Arrays.toString(EnvironmentQi.getQi(EnvironmentQi.getEnviroQi(player.blockPosition(), player.level(),input),input))));
                return 1;
            case "channelspirit":
                EnvironmentQi.channelSpirit(player.blockPosition(), player.level(), input);
                return 1;
            case "cultivate":
                player.getCapability(SpiritProvider.SPIRITCAP).ifPresent(sp -> {
                    sp.currentcultivation = Datalist.cultivationmethods[input].create();
                    sp.currentcultivation.startCultivation(player);
                });
                return 1;
            default:
                player.sendSystemMessage(Component.literal("Invalid input"));
                return 0;
        }
    }

    public static int xset(ServerPlayer player, String value, float input){
        SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
        switch (value){
            case "qi": sp.setQi((int) input);Messages.sendToClient(new QiSyncS2C(sp.getQi()), player);return 1;
            case "maxqi": sp.setMaxQi((int) input);Messages.sendToClient(new MaxQiS2C(sp.maxqi), player); return 1;

            case "maxqix": sp.setMaxqiX(input); return 1;
            case "qiregen": sp.setQiregen(input); return 1;
            case "spelldamage": sp.setSpelldamage(input); return 1;
            case "spellresist": sp.setSpellresist(input); return 1;
            case "spellcost": sp.setSpellcost(input); return 1;

            case "cxp": sp.setCultivation(input); return 1;
            case "inspiration": sp.inspiration = (int) input; return 1;
            case "metal": sp.setMetal((int) input); return 1;
            case "water": sp.setWater((int) input); return 1;
            case "wood": sp.setWood((int) input); return 1;
            case "fire": sp.setFire((int) input); return 1;
            case "earth": sp.setEarth((int) input); return 1;
            case "elementlimit": sp.setElementlimit((int) input); return 1;

            case "node": Datalist.nodes[(int) input].unlock(player);

            case "state": sp.state = Datalist.states[(int) input]; return 1;
            //case "type": sp.setType(Datalist.types[(int) input]); return 1;
            case "cmethod": sp.cultivationmethods.add(Datalist.cultivationmethods[(int) input]);return 1;
            case "spell": sp.unlockedspells.add(Datalist.spells[(int) input].getNew());return 1;
            case "spellslot": sp.selectedspells[0] = (ICastable) Datalist.spells[(int) input].getNew(); return 1;
            default:
                player.sendSystemMessage(Component.literal("Invalid input"));
                return 1;
        }

    }
    public static int xset2(ServerPlayer player, String value, String input, int index){
        return 0;
    }
}
