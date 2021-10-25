package cardImpl

import Card

class Cross: Card() {
    override val symbol: String
        get() = "cross"
    override val acceptedNumbers: List<Int>
        get() = listOf(1,2,3,5,7,10,11,13,14)

}