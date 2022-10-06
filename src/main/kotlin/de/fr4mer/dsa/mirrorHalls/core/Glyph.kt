package de.fr4mer.dsa.mirrorHalls.core
import kotlin.random.Random

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
        SYMBOL_6(6);

        companion object {
            private val vals = values()

            fun random(): Symbol {
                return vals[Random.Default.nextInt(1, vals.size)]
            }
        }
    }

    /**
     * The color of the glyph.
     */
    enum class Color(val number: Int, val sign: Char, val rgb: Int) {
        RED(1, 'r', 0xFF0000),
        BROWN(2, 'u', 0x8B4513),
        BLUE(3, 'b', 0x0000FF),
        YELLOW(4, 'y', 0xFFFF00),
        GREEN(5, 'g', 0x00FF00),
        PURPLE(6, 'p', 0x9400D3);

        companion object {
            private val vals = values()

            fun random(): Color {
                return vals[Random.Default.nextInt(1, vals.size)]
            }
        }
    }

    fun getGlyphString(): String {
        return "${symbol.number}${color.sign}"
    }

    companion object {
        fun random(): Glyph {
            return Glyph(Symbol.random(), Color.random())
        }
    }
}