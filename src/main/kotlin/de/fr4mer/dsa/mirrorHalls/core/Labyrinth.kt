package de.fr4mer.dsa.mirrorHalls.core

/**
 * Base class for the labyrinth of rooms.
 *
 * @author Tobias Hess
 * @since 01.10.2022
 */
class Labyrinth {

    private val rooms: MutableSet<Room> = HashSet()

    private var currentRoom: Room = Room(RoomContent.START_ROOM)
    private var glyphRedHerringActive: Boolean = true

    /**
     * Moves from the current room to the next room in specified direction.
     * @param[direction] The direction to move to
     */
    fun move(direction: Direction){
        TODO("move to the next room -> next room will be new current room")
    }

    fun shuffle(){
        TODO("Not implemented yet.")
    }

    fun print(){
        TODO("Print the state of the labyrinth to console.")
    }
}