package crimsonfluff.crimsonbloodmelons.init;

import crimsonfluff.crimsonbloodmelons.CrimsonBloodMelons;
import crimsonfluff.crimsonbloodmelons.blocks.BlockMelonBase;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class blocksInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CrimsonBloodMelons.MOD_ID);

    public static final RegistryObject<Block> MELON1_BLOCK = BLOCKS.register("melon_1", BlockMelonBase::new);
    public static final RegistryObject<Block> MELON2_BLOCK = BLOCKS.register("melon_2", BlockMelonBase::new);
    public static final RegistryObject<Block> MELON3_BLOCK = BLOCKS.register("melon_3", BlockMelonBase::new);
    public static final RegistryObject<Block> MELON4_BLOCK = BLOCKS.register("melon_4", BlockMelonBase::new);
    public static final RegistryObject<Block> MELON5_BLOCK = BLOCKS.register("melon_5", BlockMelonBase::new);
}
