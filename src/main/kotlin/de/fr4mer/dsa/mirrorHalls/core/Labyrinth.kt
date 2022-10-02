package de.fr4mer.dsa.mirrorHalls.core

import RoomContent
import java.util.LinkedList

/**
 * Base class for the labyrinth of rooms.
 *
 * @author Tobias Hess
 * @since 01.10.2022
 */
class Labyrinth {

    private val rooms: MutableList<Room?> = LinkedList()
    private var width = 1
    private var height = 1

    private lateinit var currentRoom: Room
    private var glyphRedHerringActive: Boolean = true

    init {
        val startRoom = Room(RoomContent.START_ROOM)
        rooms.add(startRoom)
        move(startRoom)
    }

    /**
     * Moves from the current room to the next room in specified direction.
     * @param[direction] The direction to move to
     */
    fun move(direction: Direction) {
        currentRoom.getConnectedRoom(direction)?.let { move(it) }
    }

    fun move(room: Room) {
        //TODO generate missing room connections of the room

        currentRoom = room
    }

    /**
     * Creates a new random [Room]
     * @return A new created random [Room]
     */
    private fun createRandomRoom(): Room {
        val roomContent = RoomContent.random()
        return Room(roomContent)
    }

    fun getRoom(x: Int, y: Int): Room? {
        val idx = roomIndex(x, y)
        return if (idx < rooms.size) rooms[idx] else null
    }

    /**
     * Calculates the index of a room in the list of rooms by its x, y coordinate.
     * @param[x] The x position of the room
     * @param[y] The y position of the room
     * @return The index of the room in the [rooms] list.
     */
    private fun roomIndex(x: Int, y: Int): Int {
        return y * width + x
    }

    fun shuffle() {
        TODO("Not implemented yet.")
    }

    /**
     * Prints the labyrinth to console
     */
    fun print() {
        for (y in 0 until height) {
            val first = roomIndex(0, y)
            val last = first + width
            printRoomsRow(rooms.subList(first, last))
        }
    }

    /**
     * Prints the given list of rooms in one line to the console.
     */
    private fun printRoomsRow(rooms: List<Room?>) {
        var topRow = ""
        var fillerRow = ""
        var middleRow = ""
        var bottomRow = ""

        for (room in rooms) {
            topRow += getTopRow(room)
            fillerRow += getFillerRow(room)
            middleRow += getMiddleRow(room)
            bottomRow += getBottomRow(room)
        }

        println(topRow)
        println(fillerRow)
        println(middleRow)
        println(fillerRow)
        println(bottomRow)
    }

    private fun getTopRow(room: Room?): String {
        val glyph = room?.getConnection(Direction.NORTH)?.glyph?.getGlyphString() ?: "??"
        return "┼────$glyph────┼"
    }

    private fun getFillerRow(room: Room?): String {
        return "│          │"
    }

    private fun getMiddleRow(room: Room?): String {
        val glyphWest = room?.getConnection(Direction.WEST)?.glyph?.getGlyphString() ?: "??"
        val glyphEast = room?.getConnection(Direction.EAST)?.glyph?.getGlyphString() ?: "??"
        val content = room?.content?.number?.toString() ?: "?"
        return "$glyphWest   $content    $glyphEast"
    }

    private fun getBottomRow(room: Room?): String {
        val glyph = room?.getConnection(Direction.SOUTH)?.glyph?.getGlyphString() ?: "??"
        return "┼────$glyph────┼"
    }
}