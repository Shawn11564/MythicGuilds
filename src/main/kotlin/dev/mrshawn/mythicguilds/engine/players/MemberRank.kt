package dev.mrshawn.mythicguilds.engine.players

import dev.mrshawn.mythicguilds.utils.messages.system.MSG

enum class MemberRank(private val priority: Int, val msg: MSG) {

	LEADER(4, MSG.ROLE_LEADER),
	COLEADER(3, MSG.ROLE_COLEADER),
	OFFICER(2, MSG.ROLE_OFFICER),
	MEMBER(1, MSG.ROLE_MEMBER),
	RECRUIT(0, MSG.ROLE_RECRUIT);

	companion object {

		fun getByPriority(priority: Int): MemberRank? {
			return when (priority) {
				4 -> LEADER
				3 -> COLEADER
				2 -> OFFICER
				1 -> MEMBER
				0 -> RECRUIT
				else -> null
			}
		}

		fun getByName(name: String): MemberRank? {
			return values().firstOrNull { it.msg.toString().lowercase() == name.lowercase() }
		}

	}

	fun isAtLeast(rank: MemberRank): Boolean {
		return priority >= rank.priority
	}

	fun isAtMost(rank: MemberRank): Boolean {
		return priority <= rank.priority
	}

	fun getNext(): MemberRank? {
		return getByPriority(priority + 1)
	}

	fun getPrevious(): MemberRank? {
		return getByPriority(priority - 1)
	}

	fun getAtOrAbove(): List<MemberRank> {
		return values().filter { it.isAtLeast(this) }
	}

	fun getAbove(): List<MemberRank> {
		return values().filter { it.isAtLeast(this) && it != this }
	}

	fun getAtOrBelow(): List<MemberRank> {
		return values().filter { it.isAtMost(this) }
	}

	fun getBelow(): List<MemberRank> {
		return values().filter { it.isAtMost(this) && it != this }
	}

	fun getRankNamesAtOrAbove(): List<String> {
		return getAtOrAbove().map { it.msg.toString() }
	}

	fun getRankNamesAbove(): List<String> {
		return getAbove().map { it.msg.toString() }
	}

	fun getRankNamesAtOrBelow(): List<String> {
		return getAtOrBelow().map { it.msg.toString() }
	}

	fun getRankNamesBelow(): List<String> {
		return getBelow().map { it.msg.toString() }
	}

}
