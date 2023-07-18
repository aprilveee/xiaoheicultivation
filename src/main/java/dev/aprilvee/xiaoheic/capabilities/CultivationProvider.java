package dev.aprilvee.xiaoheic.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CultivationProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> { //for cultivation xp!

    public static Capability<Cultivation> CULTIVATION = CapabilityManager.get(new CapabilityToken<Cultivation>() {
    });

    public Cultivation cultivation = null;
    private final LazyOptional<Cultivation> optional = LazyOptional.of(this::createCultivationValue);

    private Cultivation createCultivationValue() {
        if(this.cultivation == null){
            this.cultivation = new Cultivation();
        }
        return this.cultivation;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CULTIVATION){
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createCultivationValue().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createCultivationValue().loadNBTData(nbt);
    }
}
