package com.sosnitzka.ztic_addon.traits;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

/**
 * Created by Robert on 03.06.2016.
 */
public class TraitDark extends AbstractTrait {

    public TraitDark() {
        super("dark", TextFormatting.DARK_GRAY);
    }

    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
        int time = (int) world.getWorldTime();
        if (random.nextFloat() <= 0.05 || (random.nextFloat() <= 0.3 && isNight(time))) {
            player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, random.nextInt(400) + 200));
            player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, random.nextInt(400) + 200));
        }
    }


    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean wasCritical, boolean wasHit) {
        int time = (int) player.getEntityWorld().getWorldTime();
        if (random.nextFloat() <= 0.05 || (random.nextFloat() <= 0.3 && isNight(time))) {
            player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, random.nextInt(400) + 200));
            player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, random.nextInt(400) + 200));
        }
    }

    public boolean isNight(int time) {
        if (time > 12500) {
            return true;
        } else {
            return false;
        }
    }
}
