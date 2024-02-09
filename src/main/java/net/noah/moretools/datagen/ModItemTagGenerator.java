package net.noah.moretools.datagen;

import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.noah.moretools.MoreTools;
import net.noah.moretools.init.Registration;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                               CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, MoreTools.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
         this.tag(ItemTags.TRIMMABLE_ARMOR).add(
                 Registration.ENDERITE_HELMET.get(),
                 Registration.ENDERITE_CHESTPLATE.get(),
                 Registration.ENDERITE_LEGGINGS.get(),
                 Registration.ENDERITE_BOOTS.get());
    }
}
