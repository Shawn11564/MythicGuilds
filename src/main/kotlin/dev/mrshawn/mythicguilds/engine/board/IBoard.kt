package dev.mrshawn.mythicguilds.engine.board

import dev.mrshawn.mythicguilds.guilds.Guild

interface IBoard {

	companion object {
		lateinit var board: IBoard
	}

	fun getGuildAt(glocation: GLocation): Guild?

	fun getGuildNameAt(glocation: GLocation): String?

	fun setGuildAt(glocation: GLocation, guild: Guild)

	fun setGuildAt(glocation: GLocation, guildName: String)

	fun removeAt(glocation: GLocation)

	fun unclaim(glocation: GLocation, guild: Guild)

	fun unclaimAll(guild: Guild)

	fun unclaimAll(guildName: String)

	fun unclaimAllInWorld(guild: Guild, world: String)

	fun unclaimAllInWorld(guildName: String, world: String)

	// Is this location NOT completely surrounded by claims owned by this guild
	fun isBorderLocation(glocation: GLocation): Boolean

	// Is this location connected to any claims by the specified guild
	fun isConnectedLocation(glocation: GLocation, guild: Guild): Boolean

	fun hasGuildWithin(glocation: GLocation, guild: Guild, radius: Int): Boolean

	/*
	 * Wilderness
	 *  - Wilderness is a location that is not claimed by any guild
	 */

	fun isWilderness(glocation: GLocation): Boolean

	fun getWildernessGuild(): Guild

	/*
	 *  Claim count
	 */

	fun getClaimCount(guild: Guild): Int

	fun getClaimCount(guildName: String): Int

	fun getClaimCountInWorld(guild: Guild, world: String): Int

	fun getClaimCountInWorld(guildName: String, world: String): Int

	/*
	 *  Loading / Saving
	 */

	fun load()

	fun save()

}
