package dev.aprilvee.xiaoheic.network.packet;

import dev.aprilvee.xiaoheic.capability.SpellProvider;
import dev.aprilvee.xiaoheic.entity.BasicSpell;
import dev.aprilvee.xiaoheic.registry.entities;
import dev.aprilvee.xiaoheic.spell.SpellList;
import dev.aprilvee.xiaoheic.spell.SpellType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpellC2S {
    private final SpellType type;

    public SpellC2S(SpellType type){
        this.type = type;
    }

    public SpellC2S(FriendlyByteBuf buf){
        this.type = SpellList.spells[buf.readInt()];
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(type.index);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel();
            Entity spell = entities.BASIC_SPELL.get().spawn(level, (ItemStack) null, null, player.blockPosition(), MobSpawnType.COMMAND, true, false);

            spell.getCapability(SpellProvider.SPELLCAP).ifPresent(spellCap ->
                    spellCap.setType(SpellList.none));
            //BasicSpell.s

        });
        return true;
    }
}
