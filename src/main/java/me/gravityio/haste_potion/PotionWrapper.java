package me.gravityio.haste_potion;

import com.mojang.datafixers.util.Either;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PotionWrapper {
    public static final List<PotionWrapper> TO_REGISTER = new ArrayList<>();
    final List<PotionWrapper> variants = new ArrayList<>();
    Potion potion;
    int duration;
    int amplifier;
    StatusEffect effect;
    Identifier id;
    Either<Potion, PotionWrapper> input;
    Item ingredient;
    boolean hasRecipe = false;
    public PotionWrapper(@Nullable Identifier id) {
        this.id = id;
    }

    public static void register() {
        TO_REGISTER.forEach(PotionWrapper::onRegister);
    }

    public PotionWrapper setPotion(StatusEffect effect, int duration, int amplifier) {
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;

        return this;
    }

    public PotionWrapper setIdentifier(Identifier id) {
        this.id = id;

        return this;
    }

    private void setRecipe(Either<Potion, PotionWrapper> input, Item ingredient) {
        this.hasRecipe = true;

        this.input = input;
        this.ingredient = ingredient;
    }

    public PotionWrapper setRecipe(Potion input, Item ingredient) {
        this.setRecipe(Either.left(input), ingredient);

        return this;
    }

    public PotionWrapper setRecipe(PotionWrapper input, Item ingredient) {
        setRecipe(Either.right(input), ingredient);

        return this;
    }

    public PotionWrapper addVariant(PotionWrapper wrapper) {
        this.variants.add(wrapper);

        return this;
    }

    public PotionWrapper addVariant(Function<PotionWrapper, PotionWrapper> supplier) {
        this.variants.add(supplier.apply(this));
        return this;
    }

    public PotionWrapper addToRegistry() {
        TO_REGISTER.add(this);

        return this;
    }

    private void build() {
        this.potion = new Potion(new StatusEffectInstance(this.effect, this.duration, this.amplifier));
    }

    public void onRegister() {
        this.build();

        Registry.register(Registries.POTION, this.id, this.potion);
        for (PotionWrapper variant : this.variants)
            variant.onRegister();

        if (!this.hasRecipe) return;
        var input = this.input.left().isEmpty() ? this.input.right().get().potion : this.input.left().get();
        BrewingRecipeRegistry.registerPotionRecipe(input, this.ingredient, this.potion);
    }

    public static PotionWrapper ofUpgrade(Identifier id, PotionWrapper wrapper) {
        return new PotionWrapper(id)
                .setPotion(wrapper.effect, wrapper.duration, wrapper.amplifier)
                .setRecipe(wrapper, wrapper.ingredient);
    }

    public static PotionWrapper ofUpgrade(Identifier id, PotionWrapper wrapper, int duration, int amplifier) {
        return new PotionWrapper(id)
                .setPotion(wrapper.effect, duration, amplifier)
                .setRecipe(wrapper, wrapper.ingredient);
    }

}