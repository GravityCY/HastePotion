package me.gravityio.haste_potion;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HastePotionMod implements ModInitializer {
    public static String MOD_ID = "haste_potion";
    public static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static final int MIN = 60 * 20;
    private static final IdentifierBuilder idb = new IdentifierBuilder(MOD_ID);
    private static final PotionWrapper HASTE_POTION = new PotionWrapper(idb.build("haste_potion"))
            .setPotion(StatusEffects.HASTE, MIN * 2, 0)
            .setRecipe(Potions.AWKWARD, Items.END_CRYSTAL);

    private static final PotionWrapper STRONG_HASTE_POTION = PotionWrapper.ofUpgrade(idb.build("strong_haste_potion"), HASTE_POTION, MIN, 1);
    private static final PotionWrapper STRONG_LONG_HASTE_POTION = PotionWrapper.ofUpgrade(idb.build("strong_long_haste_potion"), STRONG_HASTE_POTION, MIN * 2, 1);
    private static final PotionWrapper STRONG_LONGER_HASTE_POTION = PotionWrapper.ofUpgrade(idb.build("strong_longer_haste_potion"), STRONG_LONG_HASTE_POTION, MIN * 4, 1);
    private static final PotionWrapper STRONG_LONGERER_HASTE_POTION = PotionWrapper.ofUpgrade(idb.build("strong_longerer_haste_potion"), STRONG_LONGER_HASTE_POTION, MIN * 6, 1);
    private static final PotionWrapper STRONG_LONGEST_HASTE_POTION = PotionWrapper.ofUpgrade(idb.build("strong_longest_haste_potion"), STRONG_LONGERER_HASTE_POTION, MIN * 8, 1);

    static {
        HASTE_POTION.addVariant(STRONG_HASTE_POTION);
        STRONG_HASTE_POTION.addVariant(STRONG_LONG_HASTE_POTION);
        STRONG_LONG_HASTE_POTION.addVariant(STRONG_LONGER_HASTE_POTION);
        STRONG_LONGER_HASTE_POTION.addVariant(STRONG_LONGERER_HASTE_POTION);
        STRONG_LONGERER_HASTE_POTION.addVariant(STRONG_LONGEST_HASTE_POTION);
        HASTE_POTION.addToRegistry();
    }


    @Override
    public void onInitialize() {
        PotionWrapper.register();
    }
}
