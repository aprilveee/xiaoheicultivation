package dev.aprilvee.xiaoheic.network;

import dev.aprilvee.xiaoheic.main;
import dev.aprilvee.xiaoheic.network.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class Messages {
    private static SimpleChannel INSTANCE;

    private static int packetID = 0;
    private static int id(){
        return packetID++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(main.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        INSTANCE = net;

        net.messageBuilder(TemplateC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER) //this adds a new packet to be sendable
                .decoder(TemplateC2SPacket::new)
                .encoder(TemplateC2SPacket::toBytes)
                .consumerMainThread(TemplateC2SPacket::handle)
                .add();
        net.messageBuilder(QiSyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(QiSyncS2C::new)
                .encoder(QiSyncS2C::toBytes)
                .consumerMainThread(QiSyncS2C::handle)
                .add();
        net.messageBuilder(MaxQiS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(MaxQiS2C::new)
                .encoder(MaxQiS2C::toBytes)
                .consumerMainThread(MaxQiS2C::handle)
                .add();
        net.messageBuilder(CultivationS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(CultivationS2C::new)
                .encoder(CultivationS2C::toBytes)
                .consumerMainThread(CultivationS2C::handle)
                .add();
        net.messageBuilder(HandleCastC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(HandleCastC2S::new)
                .encoder(HandleCastC2S::toBytes)
                .consumerMainThread(HandleCastC2S::handle)
                .add();
        net.messageBuilder(StopCultivateC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(StopCultivateC2S::new)
                .encoder(StopCultivateC2S::toBytes)
                .consumerMainThread(StopCultivateC2S::handle)
                .add();
        net.messageBuilder(StartCultivateC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(StartCultivateC2S::new)
                .encoder(StartCultivateC2S::toBytes)
                .consumerMainThread(StartCultivateC2S::handle)
                .add();
        //send to server with Messages.sendToServer(new TemplateC2SPacket());
        //send to client with Messages.sendToClient(new QiSyncS2C(qi.getQi()), player);

    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }
    public static <MSG> void sendToClient(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }


}
