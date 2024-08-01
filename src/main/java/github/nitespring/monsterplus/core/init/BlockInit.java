package github.nitespring.monsterplus.core.init;



import github.nitespring.monsterplus.MonsterPlus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockInit {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK,
			 MonsterPlus.MODID);
	
	


}
