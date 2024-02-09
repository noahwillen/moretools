package net.noah.moretools.enchant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.noah.moretools.init.Registration;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HammeringEnchantment extends Enchantment {
    public HammeringEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinCost(int pEnchantmentLevel) {
        return 5 + (pEnchantmentLevel - 1) * 9;
    }

    @Override
    public int getMaxCost(int pEnchantmentLevel) {
        return this.getMinCost(pEnchantmentLevel) + 15;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    public boolean isTreasureOnly() {
        return true;
    }

    public boolean isTradeable() {
        return false;
    }

    public boolean isDiscoverable() {
        return false;
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class BlockBreakHandler {
        @SubscribeEvent
        public static void blockBreak(BlockEvent.BreakEvent event) {
            Player pPlayer = event.getPlayer();
            int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Registration.HAMMERING.get(), pPlayer);
            if (enchantmentLevel==0) { return; }

            BlockPos pPos = event.getPos();
            Level pLevel = (Level) event.getLevel();
            BlockState pState = pLevel.getBlockState(pPos);
            ItemStack pStack = pPlayer.getMainHandItem();

            Direction dir = ((BlockHitResult) pPlayer.pick(20D, 0.0F, false)).getDirection();
            BoundingBox boundingBox = switch (dir) {
                case DOWN, UP  -> new BoundingBox(pPos.getX()-enchantmentLevel, pPos.getY(), pPos.getZ()-enchantmentLevel, pPos.getX()+enchantmentLevel, pPos.getY(), pPos.getZ()+enchantmentLevel);
                case NORTH, SOUTH  -> new BoundingBox(pPos.getX()-enchantmentLevel, pPos.getY()-enchantmentLevel, pPos.getZ(), pPos.getX()+enchantmentLevel, pPos.getY()+enchantmentLevel, pPos.getZ());
                case EAST, WEST -> new BoundingBox(pPos.getX(), pPos.getY()-enchantmentLevel, pPos.getZ()-enchantmentLevel, pPos.getX(), pPos.getY()+enchantmentLevel, pPos.getZ()+enchantmentLevel);
            };
            Iterator<BlockPos> iterator = BlockPos.betweenClosedStream(boundingBox).iterator();
            while (iterator.hasNext()) {
                BlockPos pPos1 = iterator.next();
                BlockState pState1 = pLevel.getBlockState(pPos1);

                //skip the block broken by player
                if (pPos1.equals(pPos)) {
                    continue;
                }

                // skip unbreakable blocks
                if (pState1.getDestroySpeed(pLevel, pPos1)<=0) {
                    continue;
                }

                // skip blocks that aren't correct tool or not high enough tier
                if (!pStack.isCorrectToolForDrops(pState1)) {
                    continue;
                }

                pLevel.destroyBlock(pPos1, false, pPlayer);
                if (!pPlayer.isCreative()) {
                    pState.spawnAfterBreak((ServerLevel) pLevel, pPos1, pStack, true);
                    List<ItemStack> drops = Block.getDrops(pState1, (ServerLevel) pLevel, pPos1, pLevel.getBlockEntity(pPos1), pPlayer, pStack);
                    drops.forEach(e -> Block.popResource(pLevel, pPos1, e));

                    pStack.hurtAndBreak(1, pPlayer, (player) -> {
                        player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                    });
                }

                if (pStack.getMaxDamage() == pStack.getDamageValue()) {
                    break;
                }
            }
        }
    }
}
