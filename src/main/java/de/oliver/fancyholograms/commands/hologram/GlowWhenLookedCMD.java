package de.oliver.fancyholograms.commands.hologram;

import de.oliver.fancyholograms.FancyHolograms;
import de.oliver.fancyholograms.api.Hologram;
import de.oliver.fancyholograms.commands.Subcommand;
import de.oliver.fancylib.MessageHelper;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GlowWhenLookedCMD implements Subcommand {

    @Override
    public List<String> tabcompletion(@NotNull Player player, @Nullable Hologram hologram, @NotNull String[] args) {
        return null;
    }

    @Override
    public boolean run(@NotNull Player player, @Nullable Hologram hologram, @NotNull String[] args) {
        boolean glowWhenLooked = Boolean.parseBoolean(args[3]);
        if (hologram == null) {
            return false;
        }

        final var copied = hologram.getData().copy();
        copied.getDisplayData().setGlowingWhenLooked(glowWhenLooked);

        if (hologram.getData().getDisplayData().isGlowingWhenLooked() == copied.getDisplayData().isGlowingWhenLooked()) {
            MessageHelper.warning(player, "This hologram already has color set to " + glowWhenLooked);
            return false;
        }

        hologram.getData().getDisplayData().setGlowingWhenLooked(copied.getDisplayData().isGlowingWhenLooked());

        if (FancyHolograms.get().getHologramConfiguration().isSaveOnChangedEnabled()) {
            FancyHolograms.get().getHologramStorage().save(hologram);
        }

        MessageHelper.success(player, "Changed glow color to " + glowWhenLooked);
        return true;
    }
}
