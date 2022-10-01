/**
 * Describes the contents of a room.
 */
enum class RoomContent(val number: Int, val descriptionToRead: String, val description: String) {
    START_ROOM(
        1,
        "Der Raum vor euch ist exakt kubisch aufgebaut mit einer Kantenlänge von 5 Schritt. In der Mitte jeder Wand befindet sich eine 2x2 Schritt große Türe die das Gesicht eines alten lachenden Narren darstellt. Über jeder der vier Türen ist eine Kartusche mit einem leuchtenden Rune eingelassen.",
        "Dies ist der Startraum des Labyrinthes und weist keinerlei weitere Besonderheiten auf."
    ),

    ROOM_OF_HEALING(
        2,
        "Ein Raum mit den bekannten Abmessungen und den vier Türen. Der Boden des Raumes ist mit einem hellblauen Dunst überzogen.",
        "Der blaue Nebel sollte die SCs sehr skeptisch machen, heilt aber letztlich alle beteiligten. Das Durchschreiten des Raumes bringt 1 LeP (illusionär), das Einatmen des Dunstes bis zu 2W6 LeP."
    )
}