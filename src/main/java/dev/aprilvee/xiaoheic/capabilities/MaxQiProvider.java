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

public class MaxQiProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<MaxQi> MAX_QI = CapabilityManager.get(new CapabilityToken<MaxQi>() {
    });

    private MaxQi maxqi = null;
    private final LazyOptional<MaxQi> optional = LazyOptional.of(this::createMaxQi);

    private MaxQi createMaxQi() {
        if(this.maxqi == null){
            this.maxqi = new MaxQi();
        }
        return this.maxqi;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == MAX_QI){
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createMaxQi().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createMaxQi().loadNBTData(nbt);
    }
}
