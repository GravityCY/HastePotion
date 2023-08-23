package me.gravityio.haste_potion;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HastePotionMod implements ModInitializer {
    public static String MOD_ID = "haste_potion";
    public static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static final int MIN = 60*20;
    private static final IdentifierBuilder idb = new IdentifierBuilder(MOD_ID);
    private static final PotionWrapper HASTE_POTION = new PotionWrapper(idb.build("haste_potion"))
            .setPotion(StatusEffects.HASTE, MIN * 2, 0)
            .setRecipe(Potions.AWKWARD, Items.END_CRYSTAL)
            .addVariant(self ->

                    PotionWrapper.ofUpgrade(idb.build("strong_haste_potion"), self, MIN, 1)
                            .addVariant(self1 ->

                                    PotionWrapper.ofUpgrade(idb.build("strong_long_haste_potion"), self1, MIN * 2, 1)
                                            .addVariant(self2 ->

                                                    PotionWrapper.ofUpgrade(idb.build("strong_longer_haste_potion"), self2, MIN * 4, 1)
                                                            .addVariant(self3 ->

                                                                    PotionWrapper.ofUpgrade(idb.build("strong_longerer_haste_potion"), self3, MIN * 6, 1)
                                                                            .addVariant(self4 -> PotionWrapper.ofUpgrade(idb.build("strong_longest_haste_potion"), self4, MIN * 8, 1))
                                                            )
                                            )
                            )
            )
            .addToRegistry();
//    private static final PotionWrapper STRONG_HASTE_POTION = new PotionWrapper(idb.build("strong_haste_potion"))
//            .setPotion(StatusEffects.HASTE, MIN, 1)
//            .setRecipe(HASTE_POTION, Items.END_CRYSTAL)
//            .addToRegistry();
//    private static final PotionWrapper STRONG_LONG_HASTE_POTION = new PotionWrapper(idb.build("strong_long_haste_potion"))
//            .setPotion(StatusEffects.HASTE, MIN * 4, 1)
//            .setRecipe(STRONG_HASTE_POTION, Items.END_CRYSTAL)
//            .addToRegistry();
//
//    private static final PotionWrapper STRONG_LONGER_HASTE_POTION = new PotionWrapper(idb.build("strong_longer_haste_potion"))
//            .setPotion(StatusEffects.HASTE, MIN * 6, 1)
//            .setRecipe(STRONG_LONG_HASTE_POTION, Items.END_CRYSTAL)
//            .addToRegistry();
//
//    private static final PotionWrapper STRONG_LONGERER_HASTE_POTION = new PotionWrapper(idb.build("strong_longerer_haste_potion"))
//            .setPotion(StatusEffects.HASTE, MIN * 8, 1)
//            .setRecipe(STRONG_LONGER_HASTE_POTION, Items.END_CRYSTAL)
//            .addToRegistry();
//
//    private static final PotionWrapper STRONG_LONGEST_HASTE_POTION = new PotionWrapper(idb.build("strong_longest_haste_potion"))
//            .setPotion(StatusEffects.HASTE, MIN * 10, 1)
//            .setRecipe(STRONG_LONGER_HASTE_POTION, Items.END_CRYSTAL)
//            .addToRegistry();

    @Override
    public void onInitialize() {
        PotionWrapper.register();
    }
}
