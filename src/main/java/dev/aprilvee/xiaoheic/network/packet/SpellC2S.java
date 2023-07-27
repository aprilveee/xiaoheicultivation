package dev.aprilvee.xiaoheic.network.packet;

import dev.aprilvee.xiaoheic.capability.SpellCap;
import dev.aprilvee.xiaoheic.capability.SpellProvider;
import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.entity.BasicSpell;
import dev.aprilvee.xiaoheic.registry.entities;
import dev.aprilvee.xiaoheic.spell.SpellList;
import dev.aprilvee.xiaoheic.spell.SpellType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.projectile.Projectile;
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
            SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
            if(sp.getQi() >= type.qiCost && sp.getMaxqi() >= type.qiCost ){
                sp.subQi(type.qiCost);

                BasicSpell spell = new BasicSpell(player.level(), player.getEyePosition(), type.index);

                spell.setOwner(player);
                spell.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F,1.0F, 0.0F);

                level.addFreshEntity(spell);



            }
            //Projectile spell = entities.BASIC_SPELL.get().spawn(level, (ItemStack) null, null, player.blockPosition(), MobSpawnType.COMMAND, true, false);




        });
        return true;
    }
}
