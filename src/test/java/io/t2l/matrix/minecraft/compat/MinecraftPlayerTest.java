package io.t2l.matrix.minecraft.compat;

import org.junit.Test;

import java.util.UUID;

public class MinecraftPlayerTest {

    @Test(expected = IllegalArgumentException.class)
    public void testUuidRequired() {
        new MinecraftPlayer(null, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameRequired() {
        new MinecraftPlayer(UUID.randomUUID(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameRequiredChecksWhitespace() {
        new MinecraftPlayer(UUID.randomUUID(), "   ");
    }

    @Test
    public void testDisplayNameOptional() {
        // Neither should throw an exception
        new MinecraftPlayer(UUID.randomUUID(), "test", null);
        new MinecraftPlayer(UUID.randomUUID(), "test", "   ");
    }
}
