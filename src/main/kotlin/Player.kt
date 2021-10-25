class Player(val name: String = Statics.SYSTEM_NAME) {
    val playerCards = mutableListOf<String>()

    fun play(): String{
        if(name.equals(Statics.SYSTEM_NAME)){
            //The player is the system
            //The system selects a random card
            playerCards.shuffle()
            val playedCard = playerCards.get(0)
            playerCards.removeAt(0)
            return playedCard //Return string of play
        }else{
            //The player is a human
            //The user selects a card
            printPlayerCards()
            println("\nEnter the ID of the card you want to play\n " +
                    "Enter 100 to Go Market \n " +
                    "Enter 200 to Quit\n")
            val userInput = readLine()!!.toInt()
            if (userInput > 0 && userInput <= playerCards.size){
                return playerCards.get(userInput - 1)//Return string of play '1 of star'
            }else if (userInput==100){
                //go market
                return Statics.MARKET
            }else if(userInput==200){
                //Quit
                return Statics.QUIT
            }
            return Statics.INVALID
        }
    }

    fun printPlayerCards() {
        print("> Your Cards: ")
        for (i in 0..playerCards.size - 1) print(" [${i + 1} . ${playerCards.get(i)}]")
    }
}