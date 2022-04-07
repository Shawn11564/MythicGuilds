package dev.mrshawn.mythicguilds.engine.listeners

import dev.mrshawn.mythicguilds.engine.board.IBoard.Companion.board
import dev.mrshawn.mythicguilds.engine.board.GLocation
import dev.mrshawn.mythicguilds.engine.players.GPlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

object BlockBreakListener: Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	fun onBlockBreakMonitor(event: BlockBreakEvent) {
		if (event.isCancelled) return
		val player = GPlayer.get(event.player)
		val playerGuild = player?.getGuild()
		val blockGuild = board.getGuildAt(GLocation(event.block))

		if (playerGuild != blockGuild) {
			// TODO: Add guild permission to allow breaking in claimed land
			event.isCancelled = true
		}
	}


}
