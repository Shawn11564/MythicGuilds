package dev.mrshawn.mythicguilds.engine.board

import dev.mrshawn.mythicguilds.engine.board.IBoard.Companion.board
import org.bukkit.Bukkit
import org.bukkit.Chunk
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.block.BlockFace

/*
hi Shawn it's Emily, I love you!!! I'm so proud of you!!!
I love you soooo much and I am sooo proud of you! You are so smartie!
*/


data class GLocation(
	val world: String,
	val x: Int,
	val z: Int,
) {

	constructor(location: Location) : this(
		location.world?.name ?: "",
		blockToChunk(location.blockX),
		blockToChunk(location.blockZ)
	)

	constructor(chunk: Chunk) : this(
		chunk.world.name,
		chunk.x,
		chunk.z
	)

	constructor(block: Block) : this(
		block.chunk
	)

	val guild = board.getGuildAt(this)

	fun getBukkitWorld() = Bukkit.getWorld(world)

	fun getChunk() = getBukkitWorld()?.getChunkAt(x, z)

	fun getGLocationRelative(direction: BlockFace): GLocation {
		return GLocation(world, x + direction.modX, z + direction.modZ)
	}

	fun getSurroundingLocations(): List<GLocation> {
		return listOf(
			getGLocationRelative(BlockFace.NORTH),
			getGLocationRelative(BlockFace.EAST),
			getGLocationRelative(BlockFace.SOUTH),
			getGLocationRelative(BlockFace.WEST)
		)
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as GLocation

		if (world != other.world) return false
		if (x != other.x) return false
		if (z != other.z) return false

		return true
	}

	override fun hashCode(): Int {
		var result = world.hashCode()
		result = 31 * result + x
		result = 31 * result + z
		return result
	}

	override fun toString(): String {
		return "$world:$x:$z"
	}

	companion object {
		fun blockToChunk(coord: Int) = coord shr 4

		fun fromString(string: String): GLocation {
			val split = string.split(":")
			return GLocation(split[0], split[1].toInt(), split[2].toInt())
		}
	}

}
