package de.fr4mer.dsa.mirrorHalls.core

import RoomContent
import java.util.EnumMap

/**
 * A Room of the labyrinth with its content, additional notes and its connections to other rooms.
 * @author Tobias Hess
 * @since 01.10.2022
 */
data class Room(val content: RoomContent, var notes: String? = null) {

    private val connections: MutableMap<Direction, RoomConnection> = EnumMap(Direction::class.java)

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
}
