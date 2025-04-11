package net.pixeldreamstudios.gearsofvalor.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.item.weapons.weapons.HarbringerSword;

public class ItemRegistry {

        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(GearsOfValorMod.MOD_ID, Registries.ITEM);

        public static final RegistrySupplier<Item> FURYS_CALL = ITEMS.register("furys_call",
                () -> new SwordItem(Tiers.IRON, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 4, -2.5F)).rarity(Rarity.RARE).durability(250).arch$tab(TabRegistry.GEARS_TAB)));
        public static final RegistrySupplier<Item> HARBRINGER = ITEMS.register("harbringer",
                () -> new HarbringerSword(new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 9, -3.0F)).rarity(Rarity.RARE).durability(375).arch$tab(TabRegistry.GEARS_TAB)));
        public static final RegistrySupplier<Item> HEAVENS_FALL = ITEMS.register("heavens_fall",
                () -> new SwordItem(Tiers.IRON, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 9, -3.5F)).rarity(Rarity.EPIC).durability(475).arch$tab(TabRegistry.GEARS_TAB)));
        public static final RegistrySupplier<Item> SHADOW_STALKER = ITEMS.register("shadow_stalker",
                () -> new SwordItem(Tiers.IRON, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 9, -3.5F)).rarity(Rarity.EPIC).durability(475).arch$tab(TabRegistry.GEARS_TAB)));

        public static void register() {
        ITEMS.register();
    }
}
