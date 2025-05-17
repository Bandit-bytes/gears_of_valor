package net.pixeldreamstudios.gearsofvalor.registry;

import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;

public class ComponentRegistry {

    public static final DeferredRegister<DataComponentType<?>> COMPONENTS =
            DeferredRegister.create(GearsOfValorMod.MOD_ID, Registries.DATA_COMPONENT_TYPE);

    public static final DeferredSupplier<DataComponentType<Long>> LAST_HEAL_TIME = COMPONENTS.register(
            "reaper_last_heal_time",
            () -> DataComponentType.<Long>builder().persistent(Codec.LONG).build()
    );

    public static void register() {
        COMPONENTS.register();
    }
}
