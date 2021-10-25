package cardImpl

import Card

class Triangle: Card() {
    override val symbol: String
        get() = "triangle"
    override val acceptedNumbers: List<Int>
        get() = listOf(1,2,3,4,5,7,8,10,11,12,13,14)
}