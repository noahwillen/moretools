package net.noah.moretools.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.noah.moretools.MoreTools;
import net.noah.moretools.init.Registration;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MoreTools.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        trimmedArmorItem(Registration.ENDERITE_HELMET);
        trimmedArmorItem(Registration.ENDERITE_CHESTPLATE);
        trimmedArmorItem(Registration.ENDERITE_LEGGINGS);
        trimmedArmorItem(Registration.ENDERITE_BOOTS);
    }

    private void trimmedArmorItem(RegistryObject<ArmorItem> itemRegistryObject) {
        final String MOD_ID = MoreTools.MOD_ID; // Change this to your mod id

        ArmorItem armorItem = itemRegistryObject.get();

        trimMaterials.entrySet().forEach(entry -> {

            ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
            float trimValue = entry.getValue();

            String armorType = switch (armorItem.getEquipmentSlot()) {
                case HEAD -> "helmet";
                case CHEST -> "chestplate";
                case LEGS -> "leggings";
                case FEET -> "boots";
                default -> "";
            };

            String armorItemPath = "item/" + armorItem;
            String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
            String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
            ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
            ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
            ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

            // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
            // avoid an IllegalArgumentException
            existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

            // Trimmed armorItem files
            getBuilder(currentTrimName)
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", armorItemResLoc)
                    .texture("layer1", trimResLoc);

            // Non-trimmed armorItem file (normal variant)
            this.withExistingParent(itemRegistryObject.getId().getPath(),
                            mcLoc("item/generated"))
                    .override()
                    .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                    .predicate(mcLoc("trim_type"), trimValue).end()
                    .texture("layer0",
                            new ResourceLocation(MOD_ID,
                                    "item/" + itemRegistryObject.getId().getPath()));
        });
    }
}
