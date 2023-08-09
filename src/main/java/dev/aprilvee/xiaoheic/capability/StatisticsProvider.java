package dev.aprilvee.xiaoheic.capability;

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

public class StatisticsProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<StatisticsCap> STATSCAP = CapabilityManager.get(new CapabilityToken<>() {
    });

    private StatisticsCap statscap = null;
    private final LazyOptional<StatisticsCap> optional = LazyOptional.of(this::createStatsValue);

    private StatisticsCap createStatsValue() {
        if(this.statscap == null){
            this.statscap = new StatisticsCap();
        }
        return this.statscap;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == STATSCAP){
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createStatsValue().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createStatsValue().loadNBTData(nbt);
    }
}
