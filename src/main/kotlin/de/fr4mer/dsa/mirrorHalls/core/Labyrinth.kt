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

    private var currentRoom: Room = Room(RoomContent.START_ROOM)
    private var glyphRedHerringActive: Boolean = true

    init {
        rooms.add(currentRoom)
    }

    /**
     * Moves from the current room to the next room in specified direction.
     * @param[direction] The direction to move to
     */
    fun move(direction: Direction) {
        TODO("move to the next room -> next room will be new current room")
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
        val idx = roomIndex(x, y);
        return if (idx < rooms.size) rooms[idx] else null
    }

    /**
     * Calculates the index of a room in the list of rooms by its x, y coordinate.
     * @param[x] The x position of the room
     * @param[y] The y position of the room
     * @return The index of the room in the [rooms] list.
     */
    private fun roomIndex(x: Int, y: Int): Int {
        return y * width + x;
    }

    fun shuffle() {
        TODO("Not implemented yet.")
    }

    /**
     * Prints the labyrinth to console
     */
    fun print() {
        for(y in 0 until height){
            val first = roomIndex(0, y)
            val last = first + width
            printRoomsRow(rooms.subList(first, last))
        }
    }

    /**
     * Prints the given list of rooms in one line to the console.
     */
    private fun printRoomsRow(rooms: List<Room?>) {
        
    }
}