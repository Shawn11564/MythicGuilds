package dev.mrshawn.mythicguilds.engine.players

import dev.mrshawn.mythicguilds.engine.board.IBoard.Companion.board
import dev.mrshawn.mythicguilds.engine.guilds.GuildManager
import dev.mrshawn.mythicguilds.guilds.Guild
import org.bukkit.entity.Player
import java.util.*

/*
 * There may not be a GPlayer for a given player
 *  but there will always be a GPlayer for a player who is in a guild
 */
class GPlayer(
	val uuid: UUID,
	var guildID: String? = null,
	var bukkitPlayer: Player? = null,
	var power: Double = 0.0
) {

	companion object {
		fun get(player: Player) = PlayerManager.getGPlayer(player.uniqueId)
		fun get(uuid: UUID) = PlayerManager.getGPlayer(uuid)
	}

	fun getGuild(): Guild {
		// Hack to bypass smart cast to String! being impossible
		val id = guildID
		return if (id != null) {
			GuildManager.getGuild(id)
		} else {
			board.getWildernessGuild()
		}
	}

}
