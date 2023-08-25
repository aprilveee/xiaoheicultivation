package dev.aprilvee.xiaoheic.network.packet;

import dev.aprilvee.xiaoheic.client.ClientCapData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MaxQiS2C {
    private final int maxqi;

    public MaxQiS2C(int qi){
        this.maxqi = qi;
    }

    public MaxQiS2C(FriendlyByteBuf buf){
        this.maxqi = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(maxqi);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientCapData.setMaxqi(maxqi);
        });
        return true;
    }
}
