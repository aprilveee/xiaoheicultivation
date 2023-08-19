package dev.aprilvee.xiaoheic.network.packet;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.spell.ICastable;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class HandleCastC2S {
    private final int key;

    public HandleCastC2S(int key){ this.key = key;
    }

    public HandleCastC2S(FriendlyByteBuf buf){this.key = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){ buf.writeInt(key);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            if(player.gameMode.getGameModeForPlayer() != GameType.SPECTATOR){
                SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);
                player.sendSystemMessage(Component.literal(sp.selectedspells[key].getId()));
                if(sp.selectedspells[key] instanceof ICastable type){

                    int qicost = sp.getSpellCost(type);
                    if(sp.getQi() >= qicost && sp.getMaxqi() >= qicost) {
                        sp.subQi(qicost);
                        type.castSpell(player);
                    }
                }else {throw new RuntimeException("NONCASTABLE SPELL CASTED: " + sp.selectedspells[key].getId());}
            }

        });
        return true;
    }
}
