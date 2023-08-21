package dev.aprilvee.xiaoheic.network.packet;

import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.data.Datalist;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StopCultivateC2S {

	public StopCultivateC2S() {
	}
	public StopCultivateC2S(FriendlyByteBuf buf) {
	}
	public void toBytes(FriendlyByteBuf buf) {
	}

	public boolean handle(Supplier<NetworkEvent.Context> supplier){
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ServerPlayer player = context.getSender();
			player.getCapability(SpiritProvider.SPIRITCAP).ifPresent(sp -> {
			sp.currentcultivation.stopCultivation(player);
			sp.currentcultivation = Datalist.cultivationmethods[0];

			});
		});
		return true;
	}
}
