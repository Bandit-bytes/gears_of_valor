package net.pixeldreamstudios.gearsofvalor.item.weapons.client;

import mod.azure.azurelib.common.render.item.AzItemRenderer;
import mod.azure.azurelib.common.render.item.AzItemRendererConfig;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.client.GearsItemAnimator;

/**
 * Renderer shared by every weapon. Expects {@code geo/weapon/<name>.geo.json}, {@code textures/weapon/<name>.png}
 * and {@code animations/item/<name>.animation.json}.
 */
public class GearsWeaponRenderer extends AzItemRenderer {

    public GearsWeaponRenderer(String name) {
        super(AzItemRendererConfig.builder(
                        GearsOfValorMod.modResource("geo/weapon/" + name + ".geo.json"),
                        GearsOfValorMod.modResource("textures/weapon/" + name + ".png"))
                .setAnimatorProvider(() -> new GearsItemAnimator("item/", name))
                .useEntityGuiLighting()
                .useNewOffset(false)
                .build());
    }
}
