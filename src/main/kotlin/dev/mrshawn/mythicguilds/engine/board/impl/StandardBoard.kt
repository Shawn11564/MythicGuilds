package dev.mrshawn.mythicguilds.engine.board.impl

import de.leonhard.storage.Json
import dev.mrshawn.mythicguilds.MythicGuilds
import dev.mrshawn.mythicguilds.engine.board.GLocation
import dev.mrshawn.mythicguilds.engine.board.IBoard
import dev.mrshawn.mythicguilds.engine.exceptions.GuildNotFoundException
import dev.mrshawn.mythicguilds.engine.guilds.GuildManager
import dev.mrshawn.mythicguilds.engine.players.MemberList
import dev.mrshawn.mythicguilds.guilds.Guild
import dev.mrshawn.mythicguilds.utils.messages.system.MSG
import java.util.*

class StandardBoard: IBoard {

	private val map = HashMap<GLocation, Guild>()

	private val wildernessGuild = Guild(
		MSG.SYSTEM_WILDERNESS_NAME.toString(),
		MSG.SYSTEM_WILDERNESS_DESCRIPTION.toString(),
		MemberList(UUID.randomUUID())
	)

	/*
	 * StandardBoard returns a not-null guild
	 *  getWildernessGuild() is returned if no guild owns the location
	 */
	override fun getGuildAt(glocation: GLocation): Guild {
		return map[glocation] ?: getWildernessGuild()
	}

	override fun getGuildNameAt(glocation: GLocation): String? {
		return map[glocation]?.name
	}

	override fun setGuildAt(glocation: GLocation, guild: Guild) {
		map[glocation] = guild
	}
	override fun setGuildAt(glocation: GLocation, guildName: String) {
		map[glocation] = GuildManager.getGuild(guildName)
			?: throw GuildNotFoundException(MSG.EXCEPTION_GUILD_NOTFOUND)
	}

	override fun removeAt(glocation: GLocation) {
		map.remove(glocation)
	}

	override fun unclaim(glocation: GLocation, guild: Guild) {

	}
	override fun unclaimAll(guild: Guild) {
		map.values.removeIf { it == guild }
	}
	override fun unclaimAll(guildName: String) {
		unclaimAll(GuildManager.getGuild(guildName)
			?: throw GuildNotFoundException(MSG.EXCEPTION_GUILD_NOTFOUND))
	}

	override fun unclaimAllInWorld(guild: Guild, world: String) {
		map.entries.removeIf { it.key.world == world && it.value == guild }
	}
	override fun unclaimAllInWorld(guildName: String, world: String) {
		unclaimAllInWorld(GuildManager.getGuild(guildName)
			?: throw GuildNotFoundException(MSG.EXCEPTION_GUILD_NOTFOUND), world)
	}

	override fun isBorderLocation(glocation: GLocation): Boolean {
		val guild = getGuildAt(glocation) ?: return false
		for (gloc in glocation.getSurroundingLocations()) {
			val other = getGuildAt(gloc) ?: return true
			if (other != guild) return true
		}
		return false
	}

	override fun isConnectedLocation(glocation: GLocation, guild: Guild): Boolean {
		val thisGuild = getGuildAt(glocation) ?: return false
		for (gloc in glocation.getSurroundingLocations()) {
			val other = getGuildAt(gloc) ?: continue
			if (other == guild) return true
		}
		return false
	}

	override fun hasGuildWithin(glocation: GLocation, guild: Guild, radius: Int): Boolean {
		for (x in glocation.x - radius .. glocation.x + radius) {
			for (z in glocation.z - radius .. glocation.z + radius) {
				val gloc = GLocation(glocation.world, x, z)
				if (getGuildAt(gloc) == guild) return true
			}
		}
		return false
	}

	override fun isWilderness(glocation: GLocation): Boolean {
		return getGuildAt(glocation) == null
	}

	override fun getWildernessGuild(): Guild {
		return wildernessGuild
	}

	override fun getClaimCount(guild: Guild): Int {
		var count = 0
		map.values.forEach { if (it == guild) count++ }
		return count
	}
	override fun getClaimCount(guildName: String): Int {
		return getClaimCount(GuildManager.getGuild(guildName)
			?: throw GuildNotFoundException(MSG.EXCEPTION_GUILD_NOTFOUND))
	}

	override fun getClaimCountInWorld(guild: Guild, world: String): Int {
		var count = 0
		map.entries.forEach { if (it.key.world == world && it.value == guild) count++ }
		return count
	}
	override fun getClaimCountInWorld(guildName: String, world: String): Int {
		return getClaimCountInWorld(GuildManager.getGuild(guildName)
			?: throw GuildNotFoundException(MSG.EXCEPTION_GUILD_NOTFOUND), world)
	}

	private val file: Json = Json("board", MythicGuilds.instance.dataFolder.path)

	override fun load() {
		map.clear()
		for (entry in file.getStringList("board")) {
			val split = entry.split(";")
			val guild = GuildManager.getGuild(split[1]) ?: continue
			setGuildAt(GLocation.fromString(split[0]), guild)
		}
	}

	override fun save() {
		val data = ArrayList<String>()
		map.entries.forEach {
			data.add("${it.key};${it.value.name}")
		}
		file.set("board", null)
		file.set("board", data)
	}

}
