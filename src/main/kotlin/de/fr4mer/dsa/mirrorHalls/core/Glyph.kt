package de.fr4mer.dsa.mirrorHalls.core

/**
 * This class defines a glyph.
 * @author Tobias Hess
 * @since 01.10.2022
 */
data class Glyph(val symbol: Symbol, val color: Color) {

    /**
     * The symbol of a glyph.
     */
    enum class Symbol(val number: Int) {
        SYMBOL_1(1),
        SYMBOL_2(2),
        SYMBOL_3(3),
        SYMBOL_4(4),
        SYMBOL_5(5),
        SYMBOL_6(6)
    }

    /**
     * The color of the glyph.
     */
    enum class Color(val number: Int, val rgb: Int){
        RED(1, 0xFF0000),
        BROWN(2, 0x8B4513),
        BLUE(3, 0x0000FF),
        YELLOW(4, 0xFFFF00),
        GREEN(5, 0x00FF00),
        PURPLE(6, 0x9400D3)
    }
}