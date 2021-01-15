package crimsonfluff.crimsonbloodmelons.blocks;

import crimsonfluff.crimsonbloodmelons.tiles.BloodMelonTile;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class Melon_5 extends BlockMelonBase2{
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return BloodMelonTile.createMelon5();
    }
}
