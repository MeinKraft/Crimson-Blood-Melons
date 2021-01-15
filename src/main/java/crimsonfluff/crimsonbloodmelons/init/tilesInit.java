package crimsonfluff.crimsonbloodmelons.init;

import crimsonfluff.crimsonbloodmelons.CrimsonBloodMelons;
import crimsonfluff.crimsonbloodmelons.tiles.BloodMelonTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class tilesInit {
    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, CrimsonBloodMelons.MOD_ID);

    public static final RegistryObject<TileEntityType<BloodMelonTile>> BLOOD_MELON_TILE = TILES.register(
            "blood_melon_tile", () -> TileEntityType.Builder.create(BloodMelonTile::createMelon1, blocksInit.MELON1_BLOCK.get()).build(null));

    public static final RegistryObject<TileEntityType<BloodMelonTile>> BLOOD_MELON2_TILE = TILES.register(
            "blood_melon2_tile", () -> TileEntityType.Builder.create(BloodMelonTile::createMelon2, blocksInit.MELON2_BLOCK.get()).build(null));

    public static final RegistryObject<TileEntityType<BloodMelonTile>> BLOOD_MELON3_TILE = TILES.register(
            "blood_melon3_tile", () -> TileEntityType.Builder.create(BloodMelonTile::createMelon3, blocksInit.MELON3_BLOCK.get()).build(null));

    public static final RegistryObject<TileEntityType<BloodMelonTile>> BLOOD_MELON4_TILE = TILES.register(
            "blood_melon4_tile", () -> TileEntityType.Builder.create(BloodMelonTile::createMelon4, blocksInit.MELON4_BLOCK.get()).build(null));

    public static final RegistryObject<TileEntityType<BloodMelonTile>> BLOOD_MELON5_TILE = TILES.register(
            "blood_melon5_tile", () -> TileEntityType.Builder.create(BloodMelonTile::createMelon5, blocksInit.MELON5_BLOCK.get()).build(null));
}
