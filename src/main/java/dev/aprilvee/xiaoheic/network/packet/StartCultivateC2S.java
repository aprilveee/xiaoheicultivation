package dev.aprilvee.xiaoheic.network.packet;

import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.data.Datalist;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StartCultivateC2S {
    private final int index;
    public StartCultivateC2S(int index){
        this.index = index;
    }

    public StartCultivateC2S(FriendlyByteBuf buf){
        this.index = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(index);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            player.getCapability(SpiritProvider.SPIRITCAP).ifPresent(sp -> {
                sp.currentcultivation = Datalist.cultivationmethods[index].create();
                sp.currentcultivation.startCultivation(player);

            });
        });
        return true;
    }
}
