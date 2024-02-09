package net.noah.moretools.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.noah.moretools.MoreTools;
import net.noah.moretools.enchant.HammeringEnchantment;
import net.noah.moretools.items.ModArmorMaterials;
import net.noah.moretools.items.ModSmithingTemplateItem;
import net.noah.moretools.items.ModTiers;
import net.noah.moretools.items.PaxelItem;
import net.noah.moretools.loot.AddEnchantedItemModifier;
import net.noah.moretools.loot.AddItemModifier;
import net.noah.moretools.recipe.SmithingTransformWithNBTRecipe;

public class Registration {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoreTools.MOD_ID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MoreTools.MOD_ID);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MoreTools.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoreTools.MOD_ID);

    public static final TagKey<Block> PAXEL_BLOCKS = TagKey.create(Registries.BLOCK, new ResourceLocation(MoreTools.MOD_ID, "paxels"));

    public static final  DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MoreTools.MOD_ID);

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MoreTools.MOD_ID);

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        ENCHANTMENTS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        SERIALIZERS.register(modEventBus);
        LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
    }

    // PAXELS : pAttackDamageModifier & pAttackSpeedModifier mirrors the vanilla axe @ the corresponding tier
    public static final RegistryObject<Item> WOODEN_PAXEL = ITEMS.register("wooden_paxel", () -> new PaxelItem(6.0f,
            -3.2f, Tiers.WOOD, PAXEL_BLOCKS, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> STONE_PAXEL = ITEMS.register("stone_paxel", () -> new PaxelItem(7.0f,
            -3.2f, Tiers.STONE, PAXEL_BLOCKS, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> IRON_PAXEL = ITEMS.register("iron_paxel", () -> new PaxelItem(6.0f,
            -3.1f, Tiers.IRON, PAXEL_BLOCKS, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> GOLDEN_PAXEL = ITEMS.register("golden_paxel", () -> new PaxelItem(6.0f,
            -3.0f, Tiers.GOLD, PAXEL_BLOCKS, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> DIAMOND_PAXEL = ITEMS.register("diamond_paxel", () -> new PaxelItem(5.0f,
            -3.0f, Tiers.DIAMOND, PAXEL_BLOCKS, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> NETHERITE_PAXEL = ITEMS.register("netherite_paxel", () -> new PaxelItem(5.0f,
            -3.0f, Tiers.NETHERITE, PAXEL_BLOCKS, new Item.Properties().stacksTo(1).fireResistant()));

    public static final RegistryObject<Item> ENDERITE_PAXEL = ITEMS.register("enderite_paxel", () -> new PaxelItem(7.0f,
            -3.2f, ModTiers.ENDERITE, PAXEL_BLOCKS, new Item.Properties().stacksTo(1).fireResistant()));

    // ENDERITE
    public static final RegistryObject<Item> ENDERITE_AXE = ITEMS.register("enderite_axe", () -> new AxeItem(ModTiers.ENDERITE,
            6.0F, -2.0F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ENDERITE_PICKAXE = ITEMS.register("enderite_pickaxe", () -> new PickaxeItem(ModTiers.ENDERITE,
            2, -2.5F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ENDERITE_SWORD = ITEMS.register("enderite_sword", () -> new SwordItem(ModTiers.ENDERITE,
            4, -2.0F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ENDERITE_SHOVEL = ITEMS.register("enderite_shovel", () -> new ShovelItem(ModTiers.ENDERITE,
            2F, -2.5F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ENDERITE_HOE = ITEMS.register("enderite_hoe", () -> new HoeItem(ModTiers.ENDERITE,
            -3, 0.0F, new Item.Properties().fireResistant()));

    public static final RegistryObject<ArmorItem> ENDERITE_HELMET = ITEMS.register("enderite_helmet", () -> new ArmorItem(ModArmorMaterials.ENDERITE,
            ArmorItem.Type.HELMET, (new Item.Properties()).fireResistant()));

    public static final RegistryObject<ArmorItem> ENDERITE_CHESTPLATE = ITEMS.register("enderite_chestplate", () -> new ArmorItem(ModArmorMaterials.ENDERITE,
            ArmorItem.Type.CHESTPLATE, (new Item.Properties()).fireResistant()));

    public static final RegistryObject<ArmorItem> ENDERITE_LEGGINGS = ITEMS.register("enderite_leggings", () -> new ArmorItem(ModArmorMaterials.ENDERITE,
            ArmorItem.Type.LEGGINGS, (new Item.Properties()).fireResistant()));

    public static final RegistryObject<ArmorItem> ENDERITE_BOOTS = ITEMS.register("enderite_boots", () -> new ArmorItem(ModArmorMaterials.ENDERITE,
            ArmorItem.Type.BOOTS, (new Item.Properties()).fireResistant()));

    public static final RegistryObject<Block> ENDERITE_ORE_BLOCK = BLOCKS.register("enderite_ore", () -> new Block(BlockBehaviour.Properties.of().mapColor(Blocks.END_STONE.defaultMapColor()).requiresCorrectToolForDrops().strength(30.0F, 1200.0F).sound(SoundType.ANCIENT_DEBRIS)));
    public static final RegistryObject<Item> ENDERITE_ORE_ITEM = ITEMS.register("enderite_ore", () -> new BlockItem(ENDERITE_ORE_BLOCK.get(), (new Item.Properties().fireResistant())));
    public static final RegistryObject<Item> ENDERITE_NUGGET = ITEMS.register("enderite_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDERITE_INGOT = ITEMS.register("enderite_ingot", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENDERITE_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("enderite_upgrade_smithing_template",
            ModSmithingTemplateItem::createEnderiteUpgradeTemplate);



    // ENCHANTS
    public static final RegistryObject<Enchantment> HAMMERING = ENCHANTMENTS.register("hammering", HammeringEnchantment::new);

    // RECIPE SERIALIZERS
    public static final RegistryObject<RecipeSerializer<SmithingTransformWithNBTRecipe>> SMITHING_TRANSFORM_WITH_NBT =
            SERIALIZERS.register("smithing_transform_with_nbt", SmithingTransformWithNBTRecipe.Serializer::new);

    //LOOT MODIFIERS
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_item", AddItemModifier.CODEC);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ENCHANTED_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_enchanted_item", AddEnchantedItemModifier.CODEC);


    public static final RegistryObject<CreativeModeTab> PAXEL_CREATIVE_TAB = CREATIVE_MODE_TABS.register("paxels_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> NETHERITE_PAXEL.get().asItem().getDefaultInstance())
            .title(Component.translatable("creativetab.moretools"))
            .displayItems((parameters, output) -> {
                output.accept(Registration.WOODEN_PAXEL.get());
                output.accept(Registration.STONE_PAXEL.get());
                output.accept(Registration.IRON_PAXEL.get());
                output.accept(Registration.GOLDEN_PAXEL.get());
                output.accept(Registration.DIAMOND_PAXEL.get());
                output.accept(Registration.NETHERITE_PAXEL.get());
                output.accept(Registration.ENDERITE_PAXEL.get());

                output.accept(Registration.ENDERITE_HELMET.get());
                output.accept(Registration.ENDERITE_CHESTPLATE.get());
                output.accept(Registration.ENDERITE_LEGGINGS.get());
                output.accept(Registration.ENDERITE_BOOTS.get());

                output.accept(Registration.ENDERITE_AXE.get());
                output.accept(Registration.ENDERITE_PICKAXE.get());
                output.accept(Registration.ENDERITE_SWORD.get());
                output.accept(Registration.ENDERITE_SHOVEL.get());
                output.accept(Registration.ENDERITE_HOE.get());

                output.accept(Registration.ENDERITE_NUGGET.get());
                output.accept(Registration.ENDERITE_INGOT.get());
                output.accept(Registration.ENDERITE_ORE_ITEM.get());


                output.accept(Registration.ENDERITE_UPGRADE_SMITHING_TEMPLATE.get());
            }).build());

}
