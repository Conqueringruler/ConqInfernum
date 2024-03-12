package conq_.conqinfernum.item;

import conq_.conqinfernum.ConqInfernum;
import net.minecraft.core.item.Item;

import net.minecraft.core.item.tag.ItemTags;
import turniplabs.halplibe.helper.ItemHelper;
public class Infernum {


	public static final Item infernumItem = ItemHelper.createItem(ConqInfernum.MOD_ID, new Item("infernumItem", 31302), "infernum.png").withTags(ItemTags.renderFullbright);


/**	public void InitializeInfernum() // for whatever reason, the way I made items in 1.7.7.0.2 doesn't work anymore, so I just had to do it this way.
	{ // edit from the future: it didn't work either, but I got it now
		infernumItem = ItemHelper.createItem(ConqInfernum.MOD_ID, new Item("infernumItem", 31302), "infernum.png");
	} */


	public static void register() {
	}
}
