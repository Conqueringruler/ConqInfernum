package conq_.conqinfernum;


import conq_.conqinfernum.crafting.CraftingManager;
import conq_.conqinfernum.item.Infernum;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class ConqInfernum implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "conqinfernum";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    @Override
    public void onInitialize() {
        LOGGER.info("Conq_'s Infernum initialized.");
Infernum.register();
		//new Infernum().InitializeInfernum();

    }

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {
		new CraftingManager().InitializeRecipes();
	}

	@Override
	public void onRecipesReady() {

	}
}
