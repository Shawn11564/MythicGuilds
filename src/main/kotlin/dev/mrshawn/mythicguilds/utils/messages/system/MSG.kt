package dev.mrshawn.mythicguilds.utils.messages.system

enum class MSG(private val definition: String) {

	/*
	 * Messages sent when guild actions occur
	 */

	GUILD_CHUNK_CLAIMED("&e%player% &aclaimed a chunk at &e%x% &a, &e%z%"),
	GUILD_CHUNK_UNCLAIMED("&e%player% &aunclaimed a chunk at &e%x% &a, &e%z%"),

	GUILD_INVITE_SENT("&a&e%player%&a has been invited to join your guild."),
	GUILD_INVITE_RECEIVED("&aYou have been invited to join &e%guild%&a."),
	GUILD_INVITE_PLAYER_ACCEPTED("&aYou have joined &e%guild%&a."),
	GUILD_INVITE_PLAYER_JOINED("&e%player% &ahas joined your guild!."),
	GUILD_INVITE_DECLINED("&aYou have declined the invite to join &e%guild%&a."),
	GUILD_INVITE_CANCELLED("&aThe invite to join &e%guild%&a has been been cancelled."),

	/*
	 * Command messages
	 */

	USAGE_GUILD_CREATE("&cUsage: /guild create <name>"),
	USAGE_GUILD_DISBAND("&cUsage: /guild disband"),

	/*
	 * Error messages
	 */

	ERROR_NO_PERMISSION("&cYou do not have permission to do that."),
	ERROR_NO_GUILD("&cYou are not in a guild."),
	ERROR_ALREADY_IN_GUILD("&cYou are already in a guild."),

	/*
	 * Guild defaults
	 */

	DEFAULTS_GUILD_DESCRIPTION("Default guild description"),

	/*
	 * System related
	 */

	SYSTEM_WILDERNESS_NAME("Wilderness"),
	SYSTEM_WILDERNESS_DESCRIPTION("&2This is the wilderness."),

	/*
	 * Board related
	 *  - ex: changing chunk
	 */

	BOARD_LEAVE_GUILD("&aNow leaving &e%guild%&a."),
	BOARD_ENTER_GUILD("&aNow entering &e%guild%&a."),

	/*
	 * Guild ranks / roles
	 */

	ROLE_LEADER("leader"),
	ROLE_COLEADER("coleader"),
	ROLE_OFFICER("officer"),
	ROLE_MEMBER("member"),
	ROLE_RECRUIT("recruit"),

	EXCEPTION_GUILD_NOTFOUND("Unable to find a guild with that name!");

	override fun toString(): String {
		return definition
	}

}
