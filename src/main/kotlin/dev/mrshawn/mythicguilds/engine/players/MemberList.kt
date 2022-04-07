package dev.mrshawn.mythicguilds.engine.players

import dev.mrshawn.mythicguilds.MythicGuilds
import dev.mrshawn.mythicguilds.guilds.Guild
import dev.mrshawn.mythicguilds.utils.messages.Chat
import dev.mrshawn.mythicguilds.utils.messages.TextMessage
import dev.mrshawn.mythicguilds.utils.messages.TextReplacement
import dev.mrshawn.mythicguilds.utils.messages.system.MSG
import dev.mrshawn.mythicguilds.utils.messages.tell
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
import java.util.*

class MemberList(owner: UUID? = null) {

	var guild: Guild? = null
	private val members = HashMap<UUID, MemberRank>()
	private val invitedMembers = HashMap<UUID, BukkitTask>()

	init {
		if (owner != null) members[owner] = MemberRank.LEADER
	}

	fun addMember(memberUUID: UUID, rank: MemberRank) {
		members[memberUUID] = rank
	}

	fun addMember(memberUUID: UUID) = addMember(memberUUID, MemberRank.RECRUIT)

	fun getGPlayerMembers(): List<GPlayer> {
		val gPlayers = ArrayList<GPlayer>()
		members.keys.forEach { member ->
			gPlayers.add(PlayerManager.getGPlayer(member) ?: return@forEach)
		}
		return gPlayers
	}
	fun getMembers(rank: MemberRank): List<UUID> {
		return members.filter { it.value == rank }.keys.toList()
	}

	fun getMembersAtOrAbove(rank: MemberRank): List<UUID> {
		return members.filter { it.value.isAtLeast(rank) }.keys.toList()
	}

	fun getMembersAtOrBelow(rank: MemberRank): List<UUID> {
		return members.filter { it.value.isAtMost(rank) }.keys.toList()
	}

	fun inviteMember(invitedUUID: UUID) {
		val invite = object : BukkitRunnable() {
			override fun run() {
				invitedMembers.remove(invitedUUID)
				Chat.tell(invitedUUID, TextMessage()
					.addMessages(MSG.GUILD_INVITE_CANCELLED)
					.addReplacements(TextReplacement().of(
						Pair("%guild%", guild?.name ?: "")
					))
				)
			}
		}.runTaskLater(MythicGuilds.instance, 60 * 20L)

		for (member in getMembersAtOrAbove(MemberRank.COLEADER)) {
			Chat.tell(member, TextMessage()
				.addMessages(MSG.GUILD_INVITE_SENT)
				.addReplacements(TextReplacement().of(
					Pair("%player%", Bukkit.getPlayer(invitedUUID)?.name ?: ""),
				))
			)
		}
		invitedMembers[invitedUUID] = invite
	}
	fun uninviteMember(invitedUUID: UUID) {
		if (invitedMembers.containsKey(invitedUUID)) {
			invitedMembers[invitedUUID]?.cancel()
			Chat.tell(invitedUUID, TextMessage()
				.addMessages(MSG.GUILD_INVITE_CANCELLED)
				.addReplacements(TextReplacement().of(
					Pair("%guild%", guild?.name ?: "")
				))
			)
			invitedMembers.remove(invitedUUID)
		}
	}

}
