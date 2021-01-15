package crimsonfluff.crimsonbloodmelons.tiles;

import crimsonfluff.crimsonbloodmelons.CrimsonBloodMelons;
import crimsonfluff.crimsonbloodmelons.init.tilesInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public class BloodMelonTile extends TileEntity implements ITickableTileEntity {
    public BloodMelonTile(TileEntityType tileEntityTypeIn) { super(tileEntityTypeIn); }

    public static BloodMelonTile createMelon1() { return new BloodMelonTile(tilesInit.BLOOD_MELON_TILE.get()); }
    public static BloodMelonTile createMelon2() { return new BloodMelonTile(tilesInit.BLOOD_MELON2_TILE.get()); }
    public static BloodMelonTile createMelon3() { return new BloodMelonTile(tilesInit.BLOOD_MELON3_TILE.get()); }
    public static BloodMelonTile createMelon4() { return new BloodMelonTile(tilesInit.BLOOD_MELON4_TILE.get()); }
    public static BloodMelonTile createMelon5() { return new BloodMelonTile(tilesInit.BLOOD_MELON5_TILE.get()); }

    private int ticks;
    public AxisAlignedBB area;

    @Override
    public void tick() {
        if (world.isRemote()) return;

        ticks++;
        if (ticks == 10) {
            ticks = 0;

            // Already full so just exit
            if (getBlockState().get(CrimsonBloodMelons.MELON_STAGE) == 15) return;
            if (area == null) {
                int x = pos.getX();
                int y = pos.getY();
                int z = pos.getZ();
                int r = 2;

                area = new AxisAlignedBB(x - r, y, z - r, x + r, y + r, z + r);
            }

            List<LivingEntity> items = world.getEntitiesWithinAABB(LivingEntity.class, area);

            if (items.size() != 0) {
                for (LivingEntity itemIE : items) {
                    // NOTE: Checks for invulnerable and creative etc etc

                    if (itemIE.attackEntityFrom(CrimsonBloodMelons.DM, 3)) {
                        int AmountFilled = getBlockState().get(CrimsonBloodMelons.MELON_STAGE) + 1;
                        world.setBlockState(pos, getBlockState().with(CrimsonBloodMelons.MELON_STAGE, AmountFilled));

                        if (AmountFilled == 15) break;
                    }
                }
            }
        }
    }
}
