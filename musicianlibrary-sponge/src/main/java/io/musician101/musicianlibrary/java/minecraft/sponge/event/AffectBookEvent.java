package io.musician101.musicianlibrary.java.minecraft.sponge.event;

import javax.annotation.Nonnull;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Cause;
import org.spongepowered.api.event.impl.AbstractEvent;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

/**
 * @deprecated nonfunctional atm due to mixins not working properly for me
 */
@Deprecated
public class AffectBookEvent extends AbstractEvent implements Cancellable {

    @Nonnull
    private final Cause cause;
    @Nonnull
    private final Player player;
    @Nonnull
    private final Transaction<ItemStackSnapshot> transaction;
    private boolean cancelled = false;

    public AffectBookEvent(@Nonnull ServerPlayer player, @Nonnull Transaction<ItemStackSnapshot> transaction, @Nonnull Cause cause) {
        this.player = player;
        this.transaction = transaction;
        this.cause = cause;
    }

    @Nonnull
    @Override
    public Cause getCause() {
        return cause;
    }

    @Nonnull
    public Player getPlayer() {
        return player;
    }

    @Nonnull
    public Transaction<ItemStackSnapshot> getTransaction() {
        return transaction;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    /**
     * @deprecated nonfunctional atm due to mixins not working properly for me
     */
    @Deprecated
    public static class EditBookEvent extends AffectBookEvent {

        public EditBookEvent(@Nonnull ServerPlayer player, @Nonnull Transaction<ItemStackSnapshot> transaction, @Nonnull Cause cause) {
            super(player, transaction, cause);
        }
    }

    /**
     * @deprecated nonfunctional atm due to mixins not working properly for me
     */
    @Deprecated
    public static class SignBookEvent extends AffectBookEvent {

        public SignBookEvent(@Nonnull ServerPlayer player, @Nonnull Transaction<ItemStackSnapshot> transaction, @Nonnull Cause cause) {
            super(player, transaction, cause);
        }
    }
}
