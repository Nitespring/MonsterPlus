package github.nitespring.monsterplus.common.entity;

import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public abstract class CustomSpellcaster extends SpellcasterIllager {
    public CustomSpellcaster(EntityType<? extends SpellcasterIllager> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        SpawnGroupData returnValue = super.finalizeSpawn(level,difficulty,spawnType,spawnGroupData);
        //this.setCanJoinRaid(level.canSeeSky(getOnPos())||position().y()>=level().getSeaLevel()||spawnType!=MobSpawnType.NATURAL);
        this.setCanJoinRaid(spawnType==MobSpawnType.EVENT);
        return returnValue;
    }
}
