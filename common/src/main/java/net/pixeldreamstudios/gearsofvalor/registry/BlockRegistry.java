package net.pixeldreamstudios.gearsofvalor.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(GearsOfValorMod.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<Item> BLOCK_ITEMS =
            DeferredRegister.create(GearsOfValorMod.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Block> SOULINITE_ORE = BLOCKS.register("soulinite_ore",
            () -> new DropExperienceBlock(
                    BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)
                            .strength(4f)
                            .requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)
            ));
    public static final RegistrySupplier<Item> SOULINITE_ORE_ITEM = BLOCK_ITEMS.register("soulinite_ore",
            () -> new BlockItem(SOULINITE_ORE.get(), new Item.Properties().arch$tab(TabRegistry.GEARS_TAB)));

    public static final RegistrySupplier<Block> FALLEN_ORE = BLOCKS.register("fallen_ore",
            () -> new DropExperienceBlock(
                    BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)
                            .strength(4f)
                            .requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)
            ));
    public static final RegistrySupplier<Item> FALLEN_ORE_ITEM = BLOCK_ITEMS.register("fallen_ore",
            () -> new BlockItem(FALLEN_ORE.get(), new Item.Properties().arch$tab(TabRegistry.GEARS_TAB)));

    public static final RegistrySupplier<Block> DEEPSLATE_SOULINITE_ORE = BLOCKS.register("deepslate_soulinite_ore",
            () -> new DropExperienceBlock(
                    BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE)
                            .strength(4f)
                            .requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)
            ));
    public static final RegistrySupplier<Item> DEEPSLATE_SOULINITE_ORE_ITEM = BLOCK_ITEMS.register("deepslate_soulinite_ore",
            () -> new BlockItem(DEEPSLATE_SOULINITE_ORE.get(), new Item.Properties().arch$tab(TabRegistry.GEARS_TAB)));

    public static final RegistrySupplier<Block> DEEPSLATE_FALLEN_ORE = BLOCKS.register("deepslate_fallen_ore",
            () -> new DropExperienceBlock(
                    BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE)
                            .strength(4f)
                            .requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)
            ));
    public static final RegistrySupplier<Item> DEEPSLATE_FALLEN_ORE_ITEM = BLOCK_ITEMS.register("deepslate_fallen_ore",
            () -> new BlockItem(DEEPSLATE_FALLEN_ORE.get(), new Item.Properties().arch$tab(TabRegistry.GEARS_TAB)));

    public static void init() {
        BLOCKS.register();
        BLOCK_ITEMS.register();
    }
}