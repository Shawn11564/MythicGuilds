package dev.mrshawn.mythicguilds.engine.players

import org.bukkit.Bukkit
import java.util.*

object PlayerManager {

	private val players = HashMap<UUID, GPlayer>()

	fun initPlayer(uuid: UUID) {
		players[uuid] = GPlayer(
			uuid,
			bukkitPlayer = Bukkit.getPlayer(uuid),
		)
	}

	fun getGPlayer(uuid: UUID) = players[uuid]

	fun hasGuild(player: GPlayer) = player.guildID != null

}
