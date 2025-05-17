package net.pixeldreamstudios.gearsofvalor.villagers;

import dev.architectury.registry.level.entity.trade.TradeRegistry;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.pixeldreamstudios.gearsofvalor.registry.ItemRegistry;
import org.jetbrains.annotations.Nullable;

public class GearsVillagerTrades {
    public static void init() {
        TradeRegistry.registerVillagerTrade(VillagerProfession.CLERIC, 5,
                new VillagerTrades.ItemListing() {
                    @Nullable
                    @Override
                    public MerchantOffer getOffer(Entity entity, RandomSource random) {
                        return new MerchantOffer(
                                new ItemCost(Items.EMERALD, 20),
                                new ItemStack(ItemRegistry.PURE_STONE.get()),
                                30,
                                15,
                                0.2F
                        );
                    }
                }
        );
    }
}
