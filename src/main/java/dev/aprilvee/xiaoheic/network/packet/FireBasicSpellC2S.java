package dev.aprilvee.xiaoheic.network.packet;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.data.Datalist;
import dev.aprilvee.xiaoheic.entity.BasicSpell;
import dev.aprilvee.xiaoheic.data.datatype.SpellType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FireBasicSpellC2S {
    private final SpellType type;

    public FireBasicSpellC2S(SpellType type){
        this.type = type;
    }

    public FireBasicSpellC2S(FriendlyByteBuf buf){
        this.type = Datalist.spells[buf.readInt()];
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

            int qicost = sp.getSpellCost(type);
            if(sp.getQi() >= qicost && sp.getMaxqi() >= qicost){

                sp.subQi(qicost);

                BasicSpell spell = new BasicSpell(player.level(), player.getEyePosition(), type.index);

                spell.setOwner(player);
                spell.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, type.flyspeed, type.accuracy);
                level.addFreshEntity(spell);
            }

        });
        return true;
    }
}
