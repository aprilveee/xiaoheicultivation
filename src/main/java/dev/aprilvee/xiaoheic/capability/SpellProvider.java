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

public class SpellProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<SpellCap> SPELLCAP = CapabilityManager.get(new CapabilityToken<>() {
    });

    private SpellCap spellcap = null;
    private final LazyOptional<SpellCap> optional = LazyOptional.of(this::createSpellValue);

    private SpellCap createSpellValue() {
        if(this.spellcap == null){
            this.spellcap = new SpellCap();
        }
        return this.spellcap;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == SPELLCAP){
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createSpellValue().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createSpellValue().loadNBTData(nbt);
    }
}
