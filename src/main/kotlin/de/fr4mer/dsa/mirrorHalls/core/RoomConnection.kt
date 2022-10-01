package de.fr4mer.dsa.mirrorHalls.core

/**
 * A Connection between two rooms, the second Room may be null.
 * @author Tobias Hess
 * @since 01.10.2022
 */
data class RoomConnection (val r1: Room, var r2: Room?, val glyph: Glyph){

}