package dev.mrshawn.mythicguilds.guilds

import dev.mrshawn.mythicguilds.engine.board.IBoard.Companion.board
import dev.mrshawn.mythicguilds.engine.board.GLocation
import dev.mrshawn.mythicguilds.engine.guilds.GuildManager
import dev.mrshawn.mythicguilds.engine.players.MemberList
import dev.mrshawn.mythicguilds.engine.players.MemberRank
import dev.mrshawn.mythicguilds.utils.messages.Chat
import dev.mrshawn.mythicguilds.utils.messages.TextMessage
import dev.mrshawn.mythicguilds.utils.messages.TextReplacement
import dev.mrshawn.mythicguilds.utils.messages.system.MSG
import dev.mrshawn.mythicguilds.utils.messages.tell
import org.bukkit.Chunk
import org.bukkit.entity.Player
import java.util.*

class Guild(
	var name: String,
	var description: String?,
	private val memberList: MemberList = MemberList()
) {

	constructor(owner: UUID, name: String) : this(
		name, MSG.DEFAULTS_GUILD_DESCRIPTION.toString(), MemberList(owner)
	)

	init {
		memberList.guild = this
	}

	val members = memberList.getGPlayerMembers()

	fun claimChunk(chunk: Chunk, claimer: Player) {
		claimChunk(chunk)
		for (member in memberList.getMembersAtOrAbove(MemberRank.OFFICER)) {
			Chat.tell(member, TextMessage()
				.addMessages(MSG.GUILD_CHUNK_CLAIMED)
				.addReplacements(TextReplacement().of(
					Pair("%x%", chunk.x.toString()),
					Pair("%z%", chunk.z.toString()),
					Pair("%guild%", name),
					Pair("%player%", claimer.name)
				))
			)
		}
	}
	private fun claimChunk(chunk: Chunk) {
		board.setGuildAt(GLocation(chunk), this)
	}
	fun unclaimChunk(chunk: Chunk) {
		board.unclaim(GLocation(chunk), this)
	}
	fun isClaimedChunk(chunk: Chunk): Boolean {
		return board.getGuildAt(GLocation(chunk)) == this
	}

	fun rename(newName: String) {
		if (GuildManager.isGuild(newName)) {
			throw IllegalArgumentException("Guild name already taken")
		}
		GuildManager.renameGuild(this, newName)
	}
	fun changeDescription(newDescription: String) {
		description = newDescription
	}

}
