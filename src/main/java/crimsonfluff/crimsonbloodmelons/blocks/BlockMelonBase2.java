package crimsonfluff.crimsonbloodmelons.blocks;

import crimsonfluff.crimsonbloodmelons.CrimsonBloodMelons;
import crimsonfluff.crimsonbloodmelons.init.itemsInit;
import crimsonfluff.crimsonbloodmelons.tiles.BloodMelonTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Map;

public class BlockMelonBase2 extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public BlockMelonBase2() {
        super(Properties.from(Blocks.MELON));

        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(CrimsonBloodMelons.MELON_STAGE, 0));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING, CrimsonBloodMelons.MELON_STAGE);
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    // Called when block is destroyed
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BloodMelonTile tile = (BloodMelonTile) worldIn.getTileEntity(pos);
            if (tile != null) worldIn.removeTileEntity(pos);

            ((ServerWorld) worldIn).spawnParticle(ParticleTypes.POOF, pos.getX()+0.5D, pos.getY(), pos.getZ()+0.5D, 15, 0.5D, 1.5D, 0.5D, 0D);
            worldIn.playSound(null, pos, SoundEvents.BLOCK_ENDER_CHEST_OPEN, SoundCategory.BLOCKS,1F,1f);
        }
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        player.addStat(Stats.BLOCK_MINED.get(this));
        player.addExhaustion(0.005F);

// NOTE: Manually control block drops - because dropped qty is based on amount full
// NOTE: Tried loot tables, waaay too much bloat !

        Map<Enchantment, Integer> mp = EnchantmentHelper.getEnchantments(stack);
        if (mp.containsKey(Enchantments.SILK_TOUCH)) {
            worldIn.addEntity(new ItemEntity(worldIn, pos.getX()+0.5D, pos.getY(), pos.getZ()+0.5D, new ItemStack(state.getBlock())));

        } else {
            if (state.get(CrimsonBloodMelons.MELON_STAGE) == 15) {
                // NOTE: Because Null-Pointer Exception !
                int f = 0;
                if (mp.containsKey(Enchantments.FORTUNE)) f = mp.get(Enchantments.FORTUNE);

                worldIn.addEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(),
                        new ItemStack(itemsInit.MELON_SLICE.get(), worldIn.rand.nextInt(3 + f) + 1)));
            }
        }
    }

    @Override
    public boolean hasComparatorInputOverride(BlockState state) { return true; }

    @Override
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        return blockState.get(CrimsonBloodMelons.MELON_STAGE);
    }
}
