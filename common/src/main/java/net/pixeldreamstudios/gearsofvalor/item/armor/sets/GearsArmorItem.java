package net.pixeldreamstudios.gearsofvalor.item.armor.sets;

import mod.azure.azurelib.rewrite.render.armor.AzArmorRenderer;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

import java.util.function.Supplier;

public class GearsArmorItem extends ArmorItem {
    public final Supplier<AzArmorRenderer> RENDERER;

    public GearsArmorItem(Holder<ArmorMaterial> holder, Type type, Supplier<AzArmorRenderer> GearsArmorRenderer, Properties properties) {
        super(holder, type, properties);
        this.RENDERER = GearsArmorRenderer;
    }
}
