package dev.mrshawn.mythicguilds.engine.listeners.self

import dev.mrshawn.mythicguilds.engine.board.IBoard.Companion.board
import dev.mrshawn.mythicguilds.engine.board.GLocation
import dev.mrshawn.mythicguilds.engine.events.PlayerChangeChunkEvent
import dev.mrshawn.mythicguilds.utils.messages.Chat
import dev.mrshawn.mythicguilds.utils.messages.TextMessage
import dev.mrshawn.mythicguilds.utils.messages.TextReplacement
import dev.mrshawn.mythicguilds.utils.messages.system.MSG
import dev.mrshawn.mythicguilds.utils.messages.tell
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

object PlayerChangeChunkListener: Listener {

	/*
	hi shawwn i love you!
	*/

	@EventHandler(priority = EventPriority.HIGHEST)
	fun onPlayerChangeChunkHighest(event: PlayerChangeChunkEvent) {
		if (event.isCancelled) return
		val oldGuild = GLocation(event.oldChunk).guild ?: board.getWildernessGuild()
		val newGuild = GLocation(event.newChunk).guild ?: board.getWildernessGuild()

		if (oldGuild != newGuild) {
			Chat.tell(event.player, TextMessage().addMessages(
				MSG.BOARD_LEAVE_GUILD
			).addReplacements(TextReplacement().of(
				Pair("%guild%", oldGuild.name)
			)))
			Chat.tell(event.player, TextMessage().addMessages(
				MSG.BOARD_ENTER_GUILD
			).addReplacements(TextReplacement().of(
				Pair("%guild%", newGuild.name)
			)))
		}

	}

}
