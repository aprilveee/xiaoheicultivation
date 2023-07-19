package dev.aprilvee.xiaoheic.network;

import dev.aprilvee.xiaoheic.main;
import dev.aprilvee.xiaoheic.network.packet.TemplateC2SPacket;
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
        //send this with Messages.sendToServer(new TemplateC2SPacket());
    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }
    public static <MSG> void sendToClient(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }


}
