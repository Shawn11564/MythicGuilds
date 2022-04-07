package dev.mrshawn.mythicguilds.engine.guilds

import dev.mrshawn.mythicguilds.engine.board.IBoard.Companion.board
import dev.mrshawn.mythicguilds.engine.players.GPlayer
import dev.mrshawn.mythicguilds.engine.players.PlayerManager
import dev.mrshawn.mythicguilds.guilds.Guild
import java.util.*

object GuildManager {

	private val guilds = HashMap<String, Guild>()

	private fun addGuild(guild: Guild) {
		guilds[guild.name] = guild
	}
	private fun removeGuild(guild: Guild) {
		guilds.remove(guild.name)
	}

	fun createGuild(creatorUUID: UUID, guildName: String): Boolean {
		if (isGuild(guildName)) return false
		if (PlayerManager.hasGuild(GPlayer.get(creatorUUID) ?: return false)) return false
		val guild = Guild(creatorUUID, guildName)
		addGuild(guild)
		return true
	}

	fun getGuild(name: String) = guilds[name] ?: board.getWildernessGuild()

	fun isGuild(name: String) = guilds.containsKey(name)

	fun renameGuild(guild: Guild, newName: String) {
		guilds.remove(guild.name)
		guild.name = newName
		for (member in guild.members) member.guildID = guild.name
		guilds[newName] = guild
	}

}
