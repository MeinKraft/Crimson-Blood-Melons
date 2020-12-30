package crimsonfluff.crimsonbloodmelons.init;

import crimsonfluff.crimsonbloodmelons.CrimsonBloodMelons;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class itemsInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CrimsonBloodMelons.MOD_ID);

    // Items
//    public static final RegistryObject<Item> TINY_COAL = ITEMS.register("tiny_coal", ItemTinyCoal::new);

    // Block Items
    public static final RegistryObject<Item> MELON1_BLOCK = ITEMS.register("melon_1",
        () -> new BlockItem(blocksInit.MELON1_BLOCK.get(), new Item.Properties().group(CrimsonBloodMelons.TAB)));

    public static final RegistryObject<Item> MELON2_BLOCK = ITEMS.register("melon_2",
            () -> new BlockItem(blocksInit.MELON2_BLOCK.get(), new Item.Properties().group(CrimsonBloodMelons.TAB)));

    public static final RegistryObject<Item> MELON3_BLOCK = ITEMS.register("melon_3",
            () -> new BlockItem(blocksInit.MELON3_BLOCK.get(), new Item.Properties().group(CrimsonBloodMelons.TAB)));

    public static final RegistryObject<Item> MELON4_BLOCK = ITEMS.register("melon_4",
            () -> new BlockItem(blocksInit.MELON4_BLOCK.get(), new Item.Properties().group(CrimsonBloodMelons.TAB)));

    public static final RegistryObject<Item> MELON5_BLOCK = ITEMS.register("melon_5",
            () -> new BlockItem(blocksInit.MELON5_BLOCK.get(), new Item.Properties().group(CrimsonBloodMelons.TAB)));
}
