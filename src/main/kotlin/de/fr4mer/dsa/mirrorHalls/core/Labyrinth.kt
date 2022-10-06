package de.fr4mer.dsa.mirrorHalls.core

import RoomContent

/**
 * Base class for the labyrinth of rooms.
 *
 * @author Tobias Hess
 * @since 01.10.2022
 */
class Labyrinth {

    private var width = 1
    private var height = 1
    private var rooms = Array<Room?>(width * height) { null }

    private lateinit var currentRoom: Room
    private var glyphRedHerringActive: Boolean = true

    init {
        val startRoom = Room(RoomContent.START_ROOM)
        rooms[0] = startRoom
        move(startRoom)
    }

    /**
     * Moves from the current room to the next room in specified direction.
     * @param[direction] The direction to move to
     */
    fun move(direction: Direction) {
        currentRoom.getConnectedRoom(direction)?.let { move(it) }
    }

    /**
     * Moves from the current room to the specified room.
     * @param[room] The room to move to
     */
    fun move(room: Room) {
        //generate missing room connections out of the room to move to
        for (direction in Direction.values()) {
            if (!room.hasConnectedRoom(direction)) {
                val newRoom = createRandomRoom()
                room.connect(direction, newRoom, Glyph.random())

                // if the space is not available...
                var roomXY = getRoomXY(room)
                var newRoomXY = calculateNewRoomXY(roomXY, direction)
                var newRoomIdx = listRoomIndex(newRoomXY.first, newRoomXY.second)

                if (!isRoomPositionAvailable(newRoomXY)) {
                    // .. grow the labyrinth to make space
                    when (direction) {
                        Direction.NORTH -> addRoomRow(true)
                        Direction.SOUTH -> addRoomRow(false)
                        Direction.EAST -> addRoomColumn(false)
                        Direction.WEST -> addRoomColumn(true)
                    }

                    //recalculate indices because they shifted
                    roomXY = getRoomXY(room)
                    newRoomXY = calculateNewRoomXY(roomXY, direction)
                    newRoomIdx = listRoomIndex(newRoomXY.first, newRoomXY.second)
                }
                rooms[newRoomIdx] = newRoom
            }
        }
        currentRoom = room
    }

    private fun calculateNewRoomXY(roomXY: Pair<Int, Int>, direction: Direction): Pair<Int, Int>{
        return when (direction) {
            Direction.NORTH -> Pair(roomXY.first, roomXY.second - 1)
            Direction.EAST -> Pair(roomXY.first + 1, roomXY.second)
            Direction.SOUTH -> Pair(roomXY.first, roomXY.second + 1)
            Direction.WEST -> Pair(roomXY.first - 1, roomXY.second)
        }
    }

    private fun isRoomPositionAvailable(roomXY: Pair<Int, Int>): Boolean {
        return roomXY.first in 0 until width && roomXY.second in 0 until height
    }

    /**
     * Adds a row of possible rooms to the bottom of the current grid.
     * The new room spaces will be initialized with null values.
     * @param[shift]    If true all already contained rooms will get shifted one y coordinate down,
     *                  if false the contained rooms will keep their indices.
     */
    private fun addRoomRow(shift: Boolean) {
        height += 1

        val newRooms = Array<Room?>(width * height) { null }
        for (idx in rooms.indices) {
            val newIdx = if (shift) idx + width else idx
            newRooms[newIdx] = rooms[idx]
        }
        rooms = newRooms
    }

    /**
     * Adds a column of possible rooms to the right side of the current grid.
     * The new room spaces will be initialized with null values.
     * @param[shift]    If true all already contained rooms will get shifted one x coordinate right,
     *                  if false the contained rooms will keep their positions.
     */
    private fun addRoomColumn(shift: Boolean) {
        width += 1

        val newRooms = Array<Room?>(width * height) { null }
        for (idx in rooms.indices) {
            var newIdx = idx + (idx / (width - 1))
            if (shift) {
                newIdx++
            }
            newRooms[newIdx] = rooms[idx]
        }
        rooms = newRooms
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
        val idx = listRoomIndex(x, y)
        return if (idx < rooms.size) rooms[idx] else null
    }

    private fun getRoomXY(room: Room): Pair<Int, Int> {
        val roomIndex = rooms.indexOf(room)
        if (roomIndex >= 0) {
            return Pair(roomIndex % width, roomIndex / width)
        }
        throw java.lang.IllegalArgumentException("The specified room is not contained in the labyrinth")
    }

    /**
     * Calculates the index of a room in the list of rooms by its x, y coordinate.
     * @param[x] The x position of the room
     * @param[y] The y position of the room
     * @return The index of the room in the [rooms] list.
     */
    private fun listRoomIndex(x: Int, y: Int): Int {
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
            val first = listRoomIndex(0, y)
            val last = first + width
            printRoomsRow(rooms.copyOfRange(first, last))
        }
    }

    /**
     * Prints the given list of rooms in one line to the console.
     */
    private fun printRoomsRow(rooms: Array<Room?>) {
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