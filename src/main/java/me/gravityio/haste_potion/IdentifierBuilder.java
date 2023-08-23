package me.gravityio.haste_potion;

import net.minecraft.util.Identifier;

public class IdentifierBuilder {
    final String namespace;

    public IdentifierBuilder(String namespace) {
        this.namespace = namespace;
    }

    public Identifier build(String path) {
        return new Identifier(namespace, path);
    }

}
