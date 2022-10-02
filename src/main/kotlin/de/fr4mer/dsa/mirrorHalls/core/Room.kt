package de.fr4mer.dsa.mirrorHalls.core

import RoomContent
import java.util.EnumMap

/**
 * A Room of the labyrinth with its content, additional notes and its connections to other rooms.
 * @author Tobias Hess
 * @since 01.10.2022
 */
data class Room(val content: RoomContent) {

    private val connections: MutableMap<Direction, RoomConnection> = EnumMap(Direction::class.java)
    var notes: String? = null

    /**
     * Connects this [Room] with the given [room] in the specified [direction] using the given [glyph].
     * This will replace already existing connections.
     * @param[direction] The direction this room is connected with the other room.
     * @param[room] The other room to connect with
     * @param[glyph] The glyph by which those two rooms are connected by
     */
    fun connect(direction: Direction, room: Room, glyph: Glyph) {
        val connection = RoomConnection(this, room, glyph)
        putConnection(direction, connection)
        room.putConnection(direction.opposite(), connection)
    }

    private fun putConnection(direction: Direction, connection: RoomConnection) {
        connections[direction] = connection
    }

    /**
     * @return The [RoomConnection] for the given direction
     */
    fun getConnection(direction: Direction): RoomConnection?{
        return connections[direction]
    }

    /**
     * @return The [Room] that is connected with this one in the specified direction
     */
    fun getConnectedRoom(direction: Direction): Room?{
        return getConnection(direction)?.getOtherRoom(this)
    }

    fun hasConnectedRoom(direction: Direction): Boolean{
        return getConnectedRoom(direction) != null
    }
}
