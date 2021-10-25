import cardImpl.*

abstract class Card {
    abstract val symbol: String
    abstract val acceptedNumbers: List<Int>

    companion object{
        fun generateCards(): MutableList<String>{
            val allCards: MutableList<String> = mutableListOf()
            val cardsToGenerateFrom = mutableListOf<Card>(Circle(), Cross(), Square(), Star(), Triangle(), Whot())
            for(card in cardsToGenerateFrom){
                for(number in card.acceptedNumbers){
                    allCards.add("$number of ${card.symbol}")
                }
            }
            allCards.shuffle()
            return allCards
        }
    }
}