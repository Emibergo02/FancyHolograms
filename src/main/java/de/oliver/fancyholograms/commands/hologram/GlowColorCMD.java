package de.oliver.fancyholograms.commands.hologram;

import de.oliver.fancyholograms.FancyHolograms;
import de.oliver.fancyholograms.api.Hologram;
import de.oliver.fancyholograms.commands.Subcommand;
import de.oliver.fancylib.MessageHelper;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GlowColorCMD implements Subcommand {

    @Override
    public List<String> tabcompletion(@NotNull Player player, @Nullable Hologram hologram, @NotNull String[] args) {
        return null;
    }

    @Override
    public boolean run(@NotNull Player player, @Nullable Hologram hologram, @NotNull String[] args) {
        final String[] colorComponents = args[3].split(",");
        if (hologram == null) {
            return false;
        }

        if (colorComponents.length != 3) {
            MessageHelper.error(player, "Invalid color format. Use R,G,B");
            return false;
        }
        int glowColor = Color.fromRGB(Integer.parseInt(colorComponents[0]),
                Integer.parseInt(colorComponents[1]),
                Integer.parseInt(colorComponents[2]))
                .asRGB();

        final var copied = hologram.getData().copy();
        copied.getDisplayData().setGlowColor(glowColor);


        if (hologram.getData().getDisplayData().getGlowColor() == copied.getDisplayData().getGlowColor()) {
            MessageHelper.warning(player, "This hologram already has color set to " + glowColor);
            return false;
        }

        hologram.getData().getDisplayData().setGlowColor(copied.getDisplayData().getGlowColor());

        if (FancyHolograms.get().getHologramConfiguration().isSaveOnChangedEnabled()) {
            FancyHolograms.get().getHologramStorage().save(hologram);
        }

        MessageHelper.success(player, "Changed glow color to " + glowColor);
        return true;
    }
}
