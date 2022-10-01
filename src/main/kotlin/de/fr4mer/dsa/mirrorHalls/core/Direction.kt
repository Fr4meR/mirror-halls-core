package de.fr4mer.dsa.mirrorHalls.core

/**
 * Directions
 * @author Tobias Hess
 * @since 01.10.2022
 */
enum class Direction {

    NORTH, EAST, SOUTH, WEST;

    /**
     * @return The opposite of this direction, e.g. will return [SOUTH] if [NORTH].
     */
    fun opposite(): Direction {
        return when (this) {
            NORTH -> SOUTH
            EAST -> WEST
            SOUTH -> NORTH
            WEST -> EAST
        }
    }
}