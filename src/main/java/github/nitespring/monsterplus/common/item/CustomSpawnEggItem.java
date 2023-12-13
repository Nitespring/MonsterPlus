package github.nitespring.monsterplus.common.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import com.google.common.collect.ImmutableList;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.SpawnEggItem;

public class CustomSpawnEggItem extends SpawnEggItem{

	private static final List<CustomSpawnEggItem> EGGS = new ArrayList<>();

    private final Supplier<EntityType<?>> typeSupplier;

 
	@SuppressWarnings("deprecation")
	public CustomSpawnEggItem(Supplier<EntityType<?>> typeSupplier, int primaryColorIn, int secondaryColorIn, Properties builder)
    {
        super(null, primaryColorIn, secondaryColorIn, builder);
        this.typeSupplier = typeSupplier;
        EGGS.add(this);
    }

    @Override
    public EntityType<?> getType(CompoundTag tag)
    {
        return this.typeSupplier.get();
    }


    public static List<CustomSpawnEggItem> getSupplierEggs()
    {
        return ImmutableList.copyOf(EGGS);
    }
    @Mixin(SpawnEggItem.class)
    public interface SpawnEggItemMixin
    {
        @Accessor(value = "EGGS")
        static Map<EntityType<?>, SpawnEggItem> getEggMap() {
            throw new AssertionError();
        }
    }
    
	
}
