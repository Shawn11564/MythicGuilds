package dev.mrshawn.mythicguilds.utils.events

import dev.mrshawn.mythicguilds.MythicGuilds
import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.Listener

object EventUtils {

	fun <T: Event> callEvent(event: T): T {
		Bukkit.getPluginManager().callEvent(event)
		return event
	}

	fun registerEvents(vararg listeners: Listener) {
		for (listener in listeners) {
			Bukkit.getPluginManager().registerEvents(listener, MythicGuilds.instance)
		}
	}


}
