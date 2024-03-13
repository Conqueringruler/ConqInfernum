package conq_.conqinfernum.crafting;


import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.RecipeBuilder;
import conq_.conqinfernum.item.Infernum;
import conq_.conqinfernum.ConqInfernum;

public class CraftingManager {


	public void InitializeRecipes() {

		//item recipes

		RecipeBuilder.Shaped(ConqInfernum.MOD_ID, "INI", "NCN", "NNN")
			.addInput('I', Infernum.infernumItem)
			.addInput('N', Block.netherrack)
			.addInput('C', Item.coal)
			.create("nether_coal", new ItemStack(Item.nethercoal, 1));

		RecipeBuilder.Shaped(ConqInfernum.MOD_ID, "NNN", "NIN", "NNN")
			.addInput('I', Infernum.infernumItem)
			.addInput('N', Block.netherrack)
			.create("igneous_netherrack", new ItemStack(Block.netherrackIgneous, 8));
	}
}
