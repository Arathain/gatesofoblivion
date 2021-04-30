package net.Arathain.gatesofoblivion.entity.render;


import net.Arathain.gatesofoblivion.GatesOfOblivion;
import net.Arathain.gatesofoblivion.entity.MordicantEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MordicantEntityRenderer extends BipedEntityRenderer<MordicantEntity, PlayerEntityModel<MordicantEntity>> {
    private static final Identifier TEXTURE = GatesOfOblivion.ID("textures/entity/mordicant_entity.png");

    public MordicantEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new PlayerEntityModel<MordicantEntity>(0.0F, false), 0.5F);
    }

    @Override
    public Identifier getTexture(MordicantEntity zombieEntity) {
        return TEXTURE;
    }
}
