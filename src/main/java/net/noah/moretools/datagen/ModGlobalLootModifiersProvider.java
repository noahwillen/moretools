package net.noah.moretools.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.noah.moretools.MoreTools;
import net.noah.moretools.init.Registration;
import net.noah.moretools.loot.AddEnchantedItemModifier;
import net.noah.moretools.loot.AddItemModifier;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, MoreTools.MOD_ID);
    }

    @Override
    protected void start() {
        add("enderite_upgrade_template_from_end_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.3f).build() }, Registration.ENDERITE_UPGRADE_SMITHING_TEMPLATE.get()));

        add("hammering_book_from_village_toolsmith", new AddEnchantedItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_toolsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.5f).build()
        }, Items.ENCHANTED_BOOK, Registration.HAMMERING.get()));

    }
}
