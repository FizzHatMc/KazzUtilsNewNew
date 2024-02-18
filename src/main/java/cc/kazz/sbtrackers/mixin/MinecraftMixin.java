<<<<<<<< HEAD:src/main/java/org/kazz/kazzutils/mixin/MinecraftMixin.java
package org.kazz.kazzutils.mixin;
========
package cc.kazz.sbtrackers.mixin;
>>>>>>>> 6e3c57af3de041ebb4425c583bc8a9de0bf6f3ea:src/main/java/cc/kazz/sbtrackers/mixin/MinecraftMixin.java

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * An example mixin using SpongePowered's Mixin library and ObjectWeb ASM.
 *
 * @see Inject
 * @see Mixin
 */
@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "startGame", at = @At(value = "HEAD"))
    private void onStartGame(CallbackInfo ci) {
        System.out.println("This is a message from an example mod!");
    }
}
