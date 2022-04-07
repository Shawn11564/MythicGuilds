package dev.mrshawn.mythicguilds.engine.events

import org.bukkit.Chunk
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

class PlayerChangeChunkEvent(
	who: Player,
	val oldChunk: Chunk,
	val newChunk: Chunk
): PlayerEvent(who), Cancellable {

	private var cancelled: Boolean = false

	companion object {
		private val HANDLERS_LIST = HandlerList()
	}
	override fun getHandlers(): HandlerList {
		return HANDLERS_LIST
	}

	override fun isCancelled(): Boolean {
		return cancelled
	}
	override fun setCancelled(cancel: Boolean) {
		cancelled = cancel
	}

}
