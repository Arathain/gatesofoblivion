package net.Arathain.gatesofoblivion;

import net.Arathain.gatesofoblivion.entity.render.MordicantEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;


public class GatesOfOblivionClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.INSTANCE.register(GatesOfOblivion.MORDICANT,
                (entityRenderDispatcher, context) -> new MordicantEntityRenderer(entityRenderDispatcher));
    }
}
