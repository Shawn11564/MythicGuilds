package dev.mrshawn.mythicguilds.utils.messages

import dev.mrshawn.mythicguilds.utils.messages.system.MSG
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import java.util.*

object Chat {

	fun tell(toWhom: CommandSender?, message: String?) { if (toWhom != null && message != null) toWhom.sendMessage(
		colorize(message)
	) }

	fun tell(toWhom: CommandSender, messages: Array<String?>) = run { for (message in messages) { tell(toWhom, message) } }

	fun tell(toWhom: CommandSender, messages: ArrayList<String>) = run { for (message in messages) { tell(toWhom, message) } }

	fun tell(toWhom: UUID, message: String?) {
		val player = Bukkit.getPlayer(toWhom)
		if (player != null && message != null) tell(player, message)
	}

	fun tell(toWhom: UUID, messages: Array<String?>) = run { for (message in messages) { tell(toWhom, message) } }

	fun tell(toWhom: UUID, messages: ArrayList<String>) = run { for (message in messages) { tell(toWhom, message) } }

	fun tell(toWhom: CommandSender, message: MSG) = tell(toWhom, message.toString())

	fun tell(toWhom: UUID, message: MSG) = tell(toWhom, message.toString())

	fun log(message: String?) { if (message != null) Bukkit.getConsoleSender().sendMessage(colorize(message)) }

	fun error(message: String?) { if (message != null) log("&4[ERROR] $message") }

	fun broadcast(message: String?) { if (message != null) Bukkit.broadcastMessage(colorize(message)) }

	fun clearChat() = run { for (i in 0 .. 100) { Bukkit.broadcastMessage(" ") } }

	fun colorize(message: String?): String =
		if (message != null) ChatColor.translateAlternateColorCodes('&', message) else ""

}
