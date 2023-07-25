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
        net.messageBuilder(QiSyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT) //this adds a new packet to be sendable
                .decoder(QiSyncS2C::new)
                .encoder(QiSyncS2C::toBytes)
                .consumerMainThread(QiSyncS2C::handle)
                .add();
        net.messageBuilder(MaxQiS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT) //this adds a new packet to be sendable
                .decoder(MaxQiS2C::new)
                .encoder(MaxQiS2C::toBytes)
                .consumerMainThread(MaxQiS2C::handle)
                .add();
        net.messageBuilder(CultivationS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT) //this adds a new packet to be sendable
                .decoder(CultivationS2C::new)
                .encoder(CultivationS2C::toBytes)
                .consumerMainThread(CultivationS2C::handle)
                .add();
        net.messageBuilder(SpellC2S.class, id(), NetworkDirection.PLAY_TO_SERVER) //this adds a new packet to be sendable
                .decoder(SpellC2S::new)
                .encoder(SpellC2S::toBytes)
                .consumerMainThread(SpellC2S::handle)
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
