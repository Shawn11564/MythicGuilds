package dev.mrshawn.mythicguilds.engine.listeners

import dev.mrshawn.mythicguilds.engine.events.PlayerChangeChunkEvent
import dev.mrshawn.mythicguilds.utils.events.EventUtils
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

object PlayerMoveListener: Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	fun onMoveMonitor(event: PlayerMoveEvent) {
		if (event.isCancelled) return
		if (event.to == null) return
		if (event.from.chunk == event.to?.chunk) return

		val changeChunkEvent = EventUtils.callEvent(PlayerChangeChunkEvent(
			event.player,
			event.from.chunk,
			event.to!!.chunk
		))

		if (changeChunkEvent.isCancelled) event.isCancelled = true
	}

}
