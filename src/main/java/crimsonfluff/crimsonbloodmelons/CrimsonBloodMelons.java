package crimsonfluff.crimsonbloodmelons;

import crimsonfluff.crimsonbloodmelons.init.blocksInit;
import crimsonfluff.crimsonbloodmelons.init.itemsInit;
import crimsonfluff.crimsonbloodmelons.init.tilesInit;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CrimsonBloodMelons.MOD_ID)
public class CrimsonBloodMelons
{
    public static final String MOD_ID = "crimsonbloodmelons";
    public static final Logger LOGGER = LogManager.getLogger(CrimsonBloodMelons.MOD_ID);
    final IEventBus MOD_EVENTBUS = FMLJavaModLoadingContext.get().getModEventBus();

    public static final IntegerProperty MELON_STAGE = IntegerProperty.create("full",0,15);
    public static final DamageSource DM = new DamageSource("bloodmelon").setDamageBypassesArmor().setDifficultyScaled().setDamageIsAbsolute();

    public CrimsonBloodMelons() {
        tilesInit.TILES.register(MOD_EVENTBUS);
        blocksInit.BLOCKS.register(MOD_EVENTBUS);
        itemsInit.ITEMS.register(MOD_EVENTBUS);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static final ItemGroup TAB = new ItemGroup(CrimsonBloodMelons.MOD_ID) {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack createIcon() { return new ItemStack(blocksInit.MELON1_BLOCK.get()); }
    };

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();

        if (!world.isRemote) {
            PlayerEntity player = event.getPlayer();

            if (player.inventory.getCurrentItem().getItem() instanceof ShearsItem) {
                BlockPos pos = event.getPos();

                if (world.getBlockState(pos).getBlock() == Blocks.MELON) {
                    // from PumpkinBlock.java (Vanilla)
                    Direction direction = event.getFace();
                    Direction direction1 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;

                    world.playSound(null, pos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    player.attackEntityFrom(CrimsonBloodMelons.DM, 1);

                    switch (world.rand.nextInt(5)) {
                        case 0:
                            world.setBlockState(pos, blocksInit.MELON1_BLOCK.get().getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, direction1), 11);
                            break;
                        case 1:
                            world.setBlockState(pos, blocksInit.MELON2_BLOCK.get().getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, direction1), 11);
                            break;
                        case 2:
                            world.setBlockState(pos, blocksInit.MELON3_BLOCK.get().getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, direction1), 11);
                            break;
                        case 3:
                            world.setBlockState(pos, blocksInit.MELON4_BLOCK.get().getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, direction1), 11);
                            break;
                        case 4:
                            world.setBlockState(pos, blocksInit.MELON5_BLOCK.get().getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, direction1), 11);
                            break;
                    }

                    ItemEntity itementity = new ItemEntity(world,
                        (double)pos.getX() + 0.5D + (double)direction1.getXOffset() * 0.65D,
                        (double)pos.getY() + 0.1D,
                        (double)pos.getZ() + 0.5D + (double)direction1.getZOffset() * 0.65D,
                        new ItemStack(Items.MELON_SEEDS, 4));
                    itementity.setMotion(0.05D * (double)direction1.getXOffset() + world.rand.nextDouble() * 0.02D, 0.05D, 0.05D * (double)direction1.getZOffset() + world.rand.nextDouble() * 0.02D);
                    world.addEntity(itementity);

                // .damageItem checks for Creative mode !
                    player.inventory.getCurrentItem().damageItem(1, player, (playerIn) -> {
                        playerIn.sendBreakAnimation(player.inventory.player.getActiveHand());
                    });
                }
            }
        }
    }
}
