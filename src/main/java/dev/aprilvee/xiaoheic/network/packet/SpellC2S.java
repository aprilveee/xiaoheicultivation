package dev.aprilvee.xiaoheic.network.packet;

import dev.aprilvee.xiaoheic.entity.BasicSpell;
import dev.aprilvee.xiaoheic.spell.SpellType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpellC2S {
    public SpellC2S(SpellType type){

    }

    public SpellC2S(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel();
            //BasicSpell.s

        });
        return true;
    }
}
