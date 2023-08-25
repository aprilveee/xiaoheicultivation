package dev.aprilvee.xiaoheic.network.packet;

import dev.aprilvee.xiaoheic.client.ClientCapData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class QiSyncS2C {
    private final int qi;

    public QiSyncS2C(int qi){
        this.qi = qi;
    }

    public QiSyncS2C(FriendlyByteBuf buf){
        this.qi = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(qi);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientCapData.setQi(qi);
        });
        return true;
    }
}
