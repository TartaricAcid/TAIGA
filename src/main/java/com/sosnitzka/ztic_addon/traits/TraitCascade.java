package com.sosnitzka.ztic_addon.traits;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

/**
 * Created by Robert on 03.06.2016.
 */
public class TraitCascade extends AbstractTrait {

    public TraitCascade() {
        super("cascade", TextFormatting.DARK_GRAY);
    }

    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
        float f = random.nextFloat();
        float b = 0.99F * calcBonus(tool);
        if (!world.isRemote && tool.canHarvestBlock(state) && f <= b) {
            double x = pos.getX();
            double y = pos.getY();
            double z = pos.getZ();
            for (int i = random.nextInt(50); i > 0; i--) {
                int r = random.nextInt(3);
                int d = random.nextBoolean() ? 1 : -1;
                if (r == 0) x += d;
                if (r == 1) y += d;
                if (r == 2) z += d;
                BlockPos nextBlock = new BlockPos(x, y, z);
                if (world.getBlockState(nextBlock) == world.getBlockState(pos)) {
                    world.destroyBlock(nextBlock, true);
                    x = nextBlock.getX();
                    y = nextBlock.getY();
                    z = nextBlock.getZ();
                    ToolHelper.damageTool(tool, random.nextInt(2), player);
                }

            }

        }

    }

    private float calcBonus(ItemStack tool) {
        int durability = ToolHelper.getCurrentDurability(tool);
        int maxDurability = ToolHelper.getMaxDurability(tool);
        return (0.4f) / (maxDurability - 50) * (durability) + 0.55f;
    }
}
