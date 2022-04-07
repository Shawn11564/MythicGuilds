package dev.mrshawn.mythicguilds.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Subcommand
import dev.mrshawn.mythicguilds.utils.messages.Chat
import dev.mrshawn.mythicguilds.utils.messages.system.MSG
import org.bukkit.entity.Player

@CommandAlias("guild|g")
class GuildCommand: BaseCommand() {

	@Subcommand("create")
	fun onCreate(player: Player, args: Array<String>) {
		if (args.isEmpty()) {
			Chat.tell(player, MSG.USAGE_GUILD_CREATE)
			return
		}

	}

}
