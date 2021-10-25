package cardImpl

import Card

class Square: Card() {
    override val symbol: String
        get() = "square"
    override val acceptedNumbers: List<Int>
        get() = listOf(1,2,3,5,7,10,11,13,14)
}