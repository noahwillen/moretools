package net.noah.moretools.mixins;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.noah.moretools.items.PaxelItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageEnchantment.class)
public class DamageEnchantmentMixin {

    @Inject(method = "canEnchant", at = @At("HEAD"), cancellable = true )
    private void paxelCanEnchant(ItemStack pStack, CallbackInfoReturnable<Boolean> cir) {
        if (pStack.getItem() instanceof PaxelItem) cir.setReturnValue(true);
    }
}
