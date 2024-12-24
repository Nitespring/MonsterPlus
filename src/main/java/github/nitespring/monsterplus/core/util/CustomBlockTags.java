package github.nitespring.monsterplus.core.util;

import github.nitespring.monsterplus.MonsterPlus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class CustomBlockTags {

    public static final TagKey<Block> FLAME_BREAKABLE = create("flame_breakable");

    private CustomBlockTags() {
    }


    private static TagKey<Block> create(String p_203847_) {
        return TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID , p_203847_));
    }

    /*public static TagKey<Block> create(ResourceLocation name) {
        return TagKey.create(Registries.BLOCK, name);
    }*/


}
