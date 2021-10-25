package cardImpl

import Card

class Star: Card() {
    override val symbol: String
        get() = "star"
    override val acceptedNumbers: List<Int>
        get() = listOf(1,2,3,4,5,7,8)
}