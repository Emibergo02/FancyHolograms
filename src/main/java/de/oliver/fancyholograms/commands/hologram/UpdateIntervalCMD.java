package de.oliver.fancyholograms.commands.hologram;

import de.oliver.fancyholograms.FancyHolograms;
import de.oliver.fancyholograms.api.Hologram;
import de.oliver.fancyholograms.commands.Subcommand;
import de.oliver.fancylib.MessageHelper;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UpdateIntervalCMD implements Subcommand {

    @Override
    public List<String> tabcompletion(@NotNull Player player, @Nullable Hologram hologram, @NotNull String[] args) {
        return null;
    }

    @Override
    public boolean run(@NotNull Player player, @Nullable Hologram hologram, @NotNull String[] args) {
        int updateInterval = Integer.parseInt(args[3]);
        if (hologram == null) {
            return false;
        }

        final var copied = hologram.getData().copy();
        copied.getDisplayData().setUpdateInterval(updateInterval);


        if (hologram.getData().getDisplayData().getUpdateInterval() == copied.getDisplayData().getUpdateInterval()) {
            MessageHelper.warning(player, "This hologram already has update interval set to " + updateInterval);
            return false;
        }

        hologram.getData().getDisplayData().setUpdateInterval(copied.getDisplayData().getUpdateInterval());

        if (FancyHolograms.get().getHologramConfiguration().isSaveOnChangedEnabled()) {
            FancyHolograms.get().getHologramStorage().save(hologram);
        }

        MessageHelper.success(player, "Changed update interval to " + updateInterval);
        return true;
    }
}
