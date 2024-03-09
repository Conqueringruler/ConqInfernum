package conq_.conqinfernum.mixin;


import conq_.conqinfernum.EnragedInterface;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.core.entity.EntityLiving;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(value = {LivingRenderer.class}, remap = false)

public abstract class EntitySizeTestingMixin<T extends EntityLiving> extends EntityRenderer<T> {
	// I'm gonna be honest, I have no idea what the <T> is for.


	@Inject(method = "render(Lnet/minecraft/core/entity/EntityLiving;DDDFF)V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glScalef(FFF)V", shift = At.Shift.AFTER))
	//@Inject(method = "doRender(Lnet/minecraft/core/entity/EntityLiving;DDDFF)V", at = @At(value = "INVOKE", target = "Lorg/", shift = At.Shift.AFTER))
	private void makeSmall(T entity, double x, double y, double z, float yaw, float partialTick, CallbackInfo ci){
		if ((entity instanceof EnragedInterface && ((EnragedInterface) entity).conqinfernum$isEnraged())){
			GL11.glScalef(1.1f, 1.1f, 1.1f);
			GL11.glColor3d(1, 0.5, 0.04);

		}
	}

@Unique
	public boolean Enraged;








	}


