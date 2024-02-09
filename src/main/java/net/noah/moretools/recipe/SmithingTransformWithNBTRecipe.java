package net.noah.moretools.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.noah.moretools.init.Registration;

import java.util.stream.Stream;

public class SmithingTransformWithNBTRecipe extends SmithingTransformRecipe implements SmithingRecipe {
    private final ResourceLocation id;
    final Ingredient template;
    final Ingredient base;
    final Ingredient addition;
    final ItemStack result;

    public SmithingTransformWithNBTRecipe(ResourceLocation pId, Ingredient pTemplate, Ingredient pBase, Ingredient pAddition, ItemStack pResult) {
        super(pId, pTemplate, pBase, pAddition, pResult);
        this.id = pId;
        this.template = pTemplate;
        this.base = pBase;
        this.addition = pAddition;
        this.result = pResult;
    }

    @Override
    public ItemStack assemble(Container pContainer, RegistryAccess pRegistryAccess) {
        ItemStack itemstack = this.result.copy();
        CompoundTag compoundtag = pContainer.getItem(1).getTag();
        if (compoundtag != null) {
            CompoundTag compoundTag1 = compoundtag.copy();
            compoundTag1 = compoundTag1.merge(itemstack.getTag());
            itemstack.setTag(compoundTag1.copy());
        }

        return itemstack;
    }

    public static class Serializer implements RecipeSerializer<SmithingTransformWithNBTRecipe> {
        public SmithingTransformWithNBTRecipe fromJson(ResourceLocation p_266953_, JsonObject p_266720_) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getNonNull(p_266720_, "template"));
            Ingredient ingredient1 = Ingredient.fromJson(GsonHelper.getNonNull(p_266720_, "base"));
            Ingredient ingredient2 = Ingredient.fromJson(GsonHelper.getNonNull(p_266720_, "addition"));
            ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(p_266720_, "result"));
            return new SmithingTransformWithNBTRecipe(p_266953_, ingredient, ingredient1, ingredient2, itemstack);
        }

        public SmithingTransformWithNBTRecipe fromNetwork(ResourceLocation p_267117_, FriendlyByteBuf p_267316_) {
            Ingredient ingredient = Ingredient.fromNetwork(p_267316_);
            Ingredient ingredient1 = Ingredient.fromNetwork(p_267316_);
            Ingredient ingredient2 = Ingredient.fromNetwork(p_267316_);
            ItemStack itemstack = p_267316_.readItem();
            return new SmithingTransformWithNBTRecipe(p_267117_, ingredient, ingredient1, ingredient2, itemstack);
        }

        public void toNetwork(FriendlyByteBuf p_266746_, SmithingTransformWithNBTRecipe p_266927_) {
            p_266927_.template.toNetwork(p_266746_);
            p_266927_.base.toNetwork(p_266746_);
            p_266927_.addition.toNetwork(p_266746_);
            p_266746_.writeItem(p_266927_.result);
        }
    }
}
