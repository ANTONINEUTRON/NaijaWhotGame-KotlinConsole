package cardImpl

import Card

class Whot: Card() {
    override val symbol: String
        get() = "whot"
    override val acceptedNumbers: List<Int>
        get() = listOf(20,20,20,20,20)
}