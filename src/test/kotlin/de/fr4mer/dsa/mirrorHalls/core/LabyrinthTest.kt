package de.fr4mer.dsa.mirrorHalls.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 *
 *
 * @author Tobias Hess
 * @since 01.10.2022
 */
internal class LabyrinthTest{

    @Test
    fun basicTest() {
        val lab = Labyrinth()
        lab.move(Direction.NORTH)
        lab.print()
    }
}