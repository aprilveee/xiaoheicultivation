package dev.aprilvee.xiaoheic.network.packet;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.entity.BasicSpell;
import dev.aprilvee.xiaoheic.data.DataList;
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
        this.type = DataList.spells[buf.readInt()];
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
            int qicost = (int) (type.qiCost + type.pQiCost * sp.getMaxqi());
            if(sp.getQi() >= qicost && sp.getMaxqi() >= qicost){
                sp.subQi(qicost);

                BasicSpell spell = new BasicSpell(player.level(), player.getEyePosition(), type.index);

                spell.setOwner(player);
                spell.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, type.flyspeed, 0.0F);
                level.addFreshEntity(spell);



            }




        });
        return true;
    }
}
