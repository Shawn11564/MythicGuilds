package dev.mrshawn.mythicguilds

import co.aikar.commands.PaperCommandManager
import dev.mrshawn.mythicguilds.engine.board.IBoard.Companion.board
import dev.mrshawn.mythicguilds.engine.board.impl.StandardBoard
import dev.mrshawn.mythicguilds.engine.listeners.BlockBreakListener
import dev.mrshawn.mythicguilds.engine.listeners.PlayerMoveListener
import dev.mrshawn.mythicguilds.engine.listeners.self.PlayerChangeChunkListener
import dev.mrshawn.mythicguilds.utils.events.EventUtils
import org.bukkit.plugin.java.JavaPlugin

class MythicGuilds: JavaPlugin() {

	companion object {
		lateinit var instance: MythicGuilds
	}

	override fun onEnable() {
		instance = this
		loadManagers()
		loadGuilds()
		loadBoard()
		registerListeners()
		registerCommands()
	}

	override fun onDisable() {
		board.save()
	}

	private fun loadManagers() {

	}

	private fun loadGuilds() {

	}

	private fun loadBoard() {
		board = StandardBoard()

		board.load()
	}

	private fun registerListeners() {
		EventUtils.registerEvents(
			/*
			 * Bukkit listeners
			 */
			PlayerMoveListener,
			BlockBreakListener,
			/*
			 * Self listeners
			 */
			PlayerChangeChunkListener
		)
	}

	private fun registerCommands() {
		val pcm = PaperCommandManager(this)

	}

}
