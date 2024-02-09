package net.noah.moretools.items;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;
import net.noah.moretools.MoreTools;

import java.util.List;

public class ModSmithingTemplateItem extends SmithingTemplateItem {
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GOLD;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;

    private static final ResourceLocation EMPTY_SLOT_INGOT = new ResourceLocation("item/empty_slot_ingot");
    private static final Component ENDERITE_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation(MoreTools.MOD_ID,"enderite_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component ENDERITE_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(MoreTools.MOD_ID,"smithing_template.enderite_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component ENDERITE_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(MoreTools.MOD_ID,"smithing_template.enderite_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component ENDERITE_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(MoreTools.MOD_ID,"smithing_template.enderite_upgrade.base_slot_description")));
    private static final Component ENDERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(MoreTools.MOD_ID,"smithing_template.enderite_upgrade.additions_slot_description")));

    private static final ResourceLocation EMPTY_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_HOE = new ResourceLocation("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = new ResourceLocation("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = new ResourceLocation("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = new ResourceLocation("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = new ResourceLocation("item/empty_slot_pickaxe");

    public ModSmithingTemplateItem(Component pAppliesTo, Component pIngredients, Component pUpdradeDescription, Component pBaseSlotDescription, Component pAdditionsSlotDescription, List<ResourceLocation> pBaseSlotEmptyIcons, List<ResourceLocation> pAdditonalSlotEmptyIcons) {
        super(pAppliesTo, pIngredients, pUpdradeDescription, pBaseSlotDescription, pAdditionsSlotDescription, pBaseSlotEmptyIcons, pAdditonalSlotEmptyIcons);
    }

    public static SmithingTemplateItem createEnderiteUpgradeTemplate() {
        return new SmithingTemplateItem(ENDERITE_UPGRADE_APPLIES_TO, ENDERITE_UPGRADE_INGREDIENTS, ENDERITE_UPGRADE,
                ENDERITE_UPGRADE_BASE_SLOT_DESCRIPTION, ENDERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createEnderiteUpgradeIconList(), createUpgradeMaterialList());
    }
    private static List<ResourceLocation> createEnderiteUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS,
                EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }
    private static List<ResourceLocation> createUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }
}
