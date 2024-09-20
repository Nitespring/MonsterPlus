package github.nitespring.monsterplus.core.init;

import github.nitespring.monsterplus.MonsterPlus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


//@Mod.EventBusSubscriber(modid = DarkestSouls.MODID)
public class SoundInit {


    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT,
            MonsterPlus.MODID);


    public static final DeferredHolder<SoundEvent,SoundEvent> WISP_AMBIENT = build("entity.wisp.ambient");
    public static final DeferredHolder<SoundEvent,SoundEvent> WISP_HURT = build("entity.wisp.hurt");
    public static final DeferredHolder<SoundEvent,SoundEvent> WISP_DEATH = build("entity.wisp.death");
    public static final DeferredHolder<SoundEvent,SoundEvent> WISP_SQUIRT = build("entity.wisp.squirt");


    private static DeferredHolder<SoundEvent,SoundEvent> build(String id)
    {
        return SOUNDS.register(id, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MonsterPlus.MODID, id)));

    }


}