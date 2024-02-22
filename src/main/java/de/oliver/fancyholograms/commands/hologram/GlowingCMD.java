package de.oliver.fancyholograms.commands.hologram;

import de.oliver.fancyholograms.FancyHolograms;
import de.oliver.fancyholograms.api.Hologram;
import de.oliver.fancyholograms.commands.Subcommand;
import de.oliver.fancylib.MessageHelper;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GlowingCMD implements Subcommand {

    @Override
    public List<String> tabcompletion(@NotNull Player player, @Nullable Hologram hologram, @NotNull String[] args) {
        return null;
    }

    @Override
    public boolean run(@NotNull Player player, @Nullable Hologram hologram, @NotNull String[] args) {
        boolean glow = Boolean.parseBoolean(args[3]);
        if (hologram == null) {
            return false;
        }

        final var copied = hologram.getData().copy();
        copied.getDisplayData().setGlowing(glow);

        if (hologram.getData().getDisplayData().isGlowing() == copied.getDisplayData().isGlowing()) {
            MessageHelper.warning(player, "This hologram already has glowing on " + glow);
            return false;
        }

        hologram.getData().getDisplayData().setGlowing(copied.getDisplayData().isGlowing());

        if (FancyHolograms.get().getHologramConfiguration().isSaveOnChangedEnabled()) {
            FancyHolograms.get().getHologramStorage().save(hologram);
        }

        MessageHelper.success(player, "Changed glow status to " + glow);
        return true;
    }
}
