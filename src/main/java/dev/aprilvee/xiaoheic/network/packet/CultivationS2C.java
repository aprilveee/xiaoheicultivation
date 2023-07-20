package dev.aprilvee.xiaoheic.network.packet;

import dev.aprilvee.xiaoheic.client.ClientCapData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CultivationS2C {
    private final float cultivation;
    private final float metalaspect;
    private final float wateraspect;
    private final float woodaspect;
    private final float fireaspect;
    private final float earthaspect;

    public CultivationS2C(float cultivation, float metal, float water, float wood, float fire, float earth)
    {
        this.cultivation = cultivation;
        this.metalaspect = metal;
        this.wateraspect = water;
        this.woodaspect = wood;
        this.fireaspect = fire;
        this.earthaspect = earth;
    }

    public CultivationS2C(FriendlyByteBuf buf){
        this.cultivation = buf.readFloat();
        this.metalaspect = buf.readFloat();
        this.wateraspect = buf.readFloat();
        this.woodaspect = buf.readFloat();
        this.fireaspect = buf.readFloat();
        this.earthaspect = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeFloat(cultivation);
        buf.writeFloat(metalaspect);
        buf.writeFloat(wateraspect);
        buf.writeFloat(woodaspect);
        buf.writeFloat(fireaspect);
        buf.writeFloat(earthaspect);

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientCapData.setCultivation(cultivation);
            ClientCapData.setMetal(metalaspect);
            ClientCapData.setWater(wateraspect);
            ClientCapData.setWood(woodaspect);
            ClientCapData.setFire(fireaspect);
            ClientCapData.setEarth(earthaspect);
        });
        return true;
    }
}
