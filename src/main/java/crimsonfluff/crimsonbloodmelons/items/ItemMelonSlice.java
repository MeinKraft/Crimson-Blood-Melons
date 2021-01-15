package crimsonfluff.crimsonbloodmelons.items;

import crimsonfluff.crimsonbloodmelons.CrimsonBloodMelons;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class ItemMelonSlice extends Item {
    public ItemMelonSlice() {
        super(new Properties()
            .group(CrimsonBloodMelons.TAB)
            .food(new Food.Builder()
                .hunger(2)
                .saturation(1)
                .setAlwaysEdible()
                .build())
        );
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tip." + CrimsonBloodMelons.MOD_ID + ".melon_slice").mergeStyle(TextFormatting.YELLOW));

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public int getUseDuration(ItemStack stack) { return 16; }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        // NOTE: Particles MUST be set to ALL to show
        if (!worldIn.isRemote) {
            entityLiving.setHealth(entityLiving.getMaxHealth());

            ((ServerWorld) worldIn).spawnParticle(ParticleTypes.HEART, entityLiving.getPosX() , entityLiving.getPosY()+ entityLiving.getEyeHeight(), entityLiving.getPosZ() , 12, 0.5D, 0.5D, 0.5D, 0D);
        }

        return entityLiving.onFoodEaten(worldIn, stack);
    }
}