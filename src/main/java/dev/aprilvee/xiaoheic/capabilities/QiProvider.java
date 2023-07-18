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

public class QiProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<QiValue> QI = CapabilityManager.get(new CapabilityToken<QiValue>() {
    });

    private QiValue qi = null;
    private final LazyOptional<QiValue> optional = LazyOptional.of(this::createQiValue);

    private QiValue createQiValue() {
        if(this.qi == null){
            this.qi = new QiValue();
        }
        return this.qi;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == QI){
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createQiValue().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createQiValue().loadNBTData(nbt);
    }
}
