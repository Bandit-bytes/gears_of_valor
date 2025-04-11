package net.pixeldreamstudios.gearsofvalor.item.weapons.weapons;

import mod.azure.azurelib.rewrite.render.item.AzItemRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;


import java.util.function.Supplier;

public class WeaponItem extends Item {
    public final Supplier<AzItemRenderer> RENDERER;

    public WeaponItem(Tier tier, Supplier<AzItemRenderer> GearsWeaponRender,Properties properties) {
        super(properties);
        this.RENDERER = GearsWeaponRender;
    }
}
