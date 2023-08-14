package dev.aprilvee.xiaoheic.network.packet;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.data.datatype.AbilityType;
import dev.aprilvee.xiaoheic.data.datatype.SpellType;
import dev.aprilvee.xiaoheic.spell.FireSpell;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
            ServerLevel level = player.serverLevel();
            SpiritCap sp = player.getCapability(SpiritProvider.SPIRITCAP).orElse(null);

            if(sp.selectedspells[key] instanceof SpellType type){ //todo: update spelltype to icastable

                switch (type.firingtype){
                    case "proj":
                        FireSpell.BasicSpell(player, level, type);break;
                    default: break;
                }

            }else if(sp.selectedspells[key] instanceof AbilityType type){
                switch (type.id){
                    default -> {break;}
                }


                switch (type.casttype){ //can probably won't need this switvh
                    case activespell -> {}
                    case sustainspell -> {}
                    case passivetoggle -> {}
                    default -> {break;}

                }
            }


        });
        return true;
    }
}
