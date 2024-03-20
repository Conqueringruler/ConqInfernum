package conq_.conqinfernum.mixin;



import conq_.conqinfernum.EnragedInterface;
import conq_.conqinfernum.item.Infernum;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.EntityPathfinder;
import net.minecraft.core.entity.monster.EntityMonster;
import net.minecraft.core.entity.projectile.EntityFireball;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.phys.Vec3d;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(value = EntityMonster.class, remap = false)

public abstract class HostileTestingMixin extends EntityPathfinder implements EnragedInterface {
	@Shadow
	protected int attackStrength;

	@Shadow
	protected abstract Entity findPlayerToAttack();

	public HostileTestingMixin(World world) {
		super(world);
	}



	@Unique
	protected Random random = new Random();

	@Unique
	boolean SetBurnablesValues = true; //
	//@Unique
	//public boolean Enraged(){
	//	return(EnragedChance > 1);
	//}

	@Override
	public boolean conqinfernum$isEnraged() {
		return conqinfernum$EnragedChance() > 49;
	}
 	@Override public int conqinfernum$EnragedChance()
	{
		return EnragedChance;
	}
	@Unique
	public boolean activateOnce = true;

	@Unique
	boolean CheckedYet = false;

	@Unique
	public int EnragedChance;

	@Unique public int FireballTimer;

	@Inject(method = "tick", at = @At(value = "TAIL"))
	public void Tick(CallbackInfo ci) {
		if (!this.world.isClientSide) {
			if (conqinfernum$isEnraged()) {

				if (this.entityToAttack != null) {
					if (this.entityToAttack.distanceToSqr(this) < 11) {
						if (this.entityToAttack != null) { // need to do a bunch of checks for it being null
							FireballTimer += 1;
							if (FireballTimer > (50 + this.health)) {

								if (this.entityToAttack != null) { // anutha one
									double d5 = this.entityToAttack.x - this.x;
									double d6 = this.entityToAttack.bb.minY + (double) (this.entityToAttack.bbHeight / 2.5F) - (this.y + (double) (this.bbHeight / 2.5F));
									double d7 = this.entityToAttack.z - this.z;


									d5 += this.entityToAttack.x * this.entityToAttack.xd * 0.5;
									d6 += this.entityToAttack.y * this.entityToAttack.yd * 0.5;
									d7 += this.entityToAttack.z * this.entityToAttack.zd * 0.5;
									EntityFireball entityfireball = new EntityFireball(this.world, this, d5, d6, d7);

									double d8 = 4.0;
									Vec3d vec3d = this.getViewVector(1.0F);
									entityfireball.x = this.x + vec3d.xCoord * d8;
									entityfireball.y = this.y + (double) (this.bbHeight / 2.0F) + 0.5;
									entityfireball.z = this.z + vec3d.zCoord * d8;
									this.world.entityJoinedWorld(entityfireball);

									FireballTimer = 0;
								}
							}

						}
					}
				}
			}
		}
	}

	@Inject(method = "onLivingUpdate", at = @At(value = "TAIL"))
	public void onLivingUpdate(CallbackInfo ci) {
		if (!this.world.isClientSide) {
			if (activateOnce) {
				if (conqinfernum$isEnraged()) {
					EnragedChance = 51;
					activateOnce = false;
				} else {
					EnragedChance = random.nextInt(51);
					activateOnce = false;
				}


			}
		}
	if (conqinfernum$isEnraged())
	{

			if (3 > random.nextInt(5)) {
				spawnEnragedParticles();
		}
		if (!this.world.isClientSide) {
			if (!CheckedYet) {
				this.health *= 3;
				this.attackStrength *= 2;
				this.fireImmune = true;
				this.moveSpeed *= 2.5f;
				this.remainingFireTicks = 0;
				this.scoreValue += 1500;
				CheckedYet = true;


			}

		}
	}

		if (this.health <= 0) {
			if (!this.world.isClientSide) {
				if (conqinfernum$isEnraged()) {

					if (!DeadYet) {
						int InfernAmount = random.nextInt(4);


						EntityItem InfernItem = this.spawnAtLocation(new ItemStack(Infernum.infernumItem, InfernAmount), 1.0F);
						//this.world.entityJoinedWorld(InfernItem);
						DeadYet = true;


						// Switched to just using a ton of switch statements since I was the amount to change depending on the item
						//	Item[] ItemDrops;
						//	ItemDrops = new Item[]{Item.ingotGold, Item.ingotGold};
						//	int RandomItem = random.nextInt(4);
						switch (random.nextInt(8)) {

							case 1:
								EntityItem itemss = this.spawnAtLocation(new ItemStack(Item.ingotGold, 11), 1.0F);
								//this.world.entityJoinedWorld(itemss); // So w/ Minecraft do I need to declare when things are spawned for correct syncing? idk
								InfernItem = this.spawnAtLocation(new ItemStack(Infernum.infernumItem, InfernAmount), 1.0F);
								//this.world.entityJoinedWorld(InfernItem);
								DeadYet = true;
								break;
							case 2:
								itemss = this.spawnAtLocation(new ItemStack(Item.foodAppleGold, 1), 1.0F);
								//this.world.entityJoinedWorld(itemss);
								InfernItem = this.spawnAtLocation(new ItemStack(Infernum.infernumItem, InfernAmount), 1.0F);
								//this.world.entityJoinedWorld(InfernItem);
								DeadYet = true;
								break;
							case 3:
								itemss = this.spawnAtLocation(new ItemStack(Item.diamond, 1), 1.0F);
								//this.world.entityJoinedWorld(itemss);
								InfernItem = this.spawnAtLocation(new ItemStack(Infernum.infernumItem, InfernAmount), 1.0F);
								//this.world.entityJoinedWorld(InfernItem);

								DeadYet = true;
								break;
							case 4:
								itemss = this.spawnAtLocation(new ItemStack(Block.dirtScorchedRich, 21), 1.0F);
								//this.world.entityJoinedWorld(itemss);
								InfernItem = this.spawnAtLocation(new ItemStack(Infernum.infernumItem, InfernAmount), 1.0F);
								//this.world.entityJoinedWorld(InfernItem);

								DeadYet = true;
								break;
							case 5:
								itemss = this.spawnAtLocation(new ItemStack(Block.cake, 1), 1.0F);
								//this.world.entityJoinedWorld(itemss);
								InfernItem = this.spawnAtLocation(new ItemStack(Infernum.infernumItem, InfernAmount), 1.0F);
								//this.world.entityJoinedWorld(InfernItem);

								DeadYet = true;
								break;
							case 6:
								itemss = this.spawnAtLocation(new ItemStack(Item.oreRawGold, 17), 1.0F);
								//this.world.entityJoinedWorld(itemss);
								InfernItem = this.spawnAtLocation(new ItemStack(Infernum.infernumItem, InfernAmount), 1.0F);
								//this.world.entityJoinedWorld(InfernItem);

								DeadYet = true;
								break;
							case 7:
								itemss = this.spawnAtLocation(new ItemStack(Block.blockGold, 2), 1.0F);
								//this.world.entityJoinedWorld(itemss);
								InfernItem = this.spawnAtLocation(new ItemStack(Infernum.infernumItem, InfernAmount), 1.0F);
								//this.world.entityJoinedWorld(InfernItem);

								DeadYet = true;
								break;
						}


					}
				}

			}
		}
	}





		@Unique float PN = 1;
		@Unique
	void spawnEnragedParticles()
	{
		double randdouble1 = random.nextGaussian() * 0.02;
		double randdouble2  = random.nextGaussian() * 0.02;
		double randdouble3 = random.nextGaussian() * 0.02;
		//double randdouble3 = random.nextDouble(0.3); // I have no idea why this doesn't work tbh.
		if (random.nextBoolean())
		{
			PN *= -1;
		}
		world.spawnParticle("flame",
			this.x + (double)(this.random.nextFloat() * this.bbWidth * 2.0F) - (double)this.bbWidth, // code from breeding animals particles
			this.y + 0.5 + (double)(this.random.nextFloat() * this.bbHeight),
			this.z + (double)(this.random.nextFloat() * this.bbWidth * 2.0F) - (double)this.bbWidth,
			(double)PN * randdouble3,
			(double)PN * randdouble2,
			(double)PN * randdouble1);


	}
	@Unique boolean DeadYet = false;


		}








