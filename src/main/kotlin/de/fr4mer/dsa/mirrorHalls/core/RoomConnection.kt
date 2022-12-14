package de.fr4mer.dsa.mirrorHalls.core

/**
 * A Connection between two rooms, the second Room may be null.
 * @author Tobias Hess
 * @since 01.10.2022
 */
data class RoomConnection (val r1: Room, var r2: Room?, val glyph: Glyph){
    /**
     * Gets the [Room] of that connection that is not the specified [room].
     * If [room] is neither of the rooms of that connection this method will return null.
     * @param[room] The [Room] to get the other one of.
     * @return The other [Room]
     */
    fun getOtherRoom(room: Room): Room? {
        return when(room){
            r1 -> r2
            r2 -> r1
            else -> null
        }
    }

}