import Card.Companion.generateCards

class PlayGame {
    lateinit var allCards: MutableList<String>
    lateinit var player1: Player
    lateinit var player2: Player
    lateinit var currPlayer: Player
    lateinit var cardOnTop: String

    fun initiate(){
        allCards = generateCards()

        //Instantiate players
        player1 = Player()
        println("What is your name")
        val name = readLine()!!
        player2 = Player(name.toLowerCase())
        currPlayer = player2//User starts play

        //Show welcome message
        println("> Hi ${player2.name} \nWelcome to Antoni Console-Base Whot Player \n" +
                "\tYou will play against the system")

        //share card
        shareCard()

        //Show starting card
        cardOnTop = allCards.get(0)//set the top card
        allCards.removeAt(0)
        showPile()

        println("\t${getAppropriateName()} Are to play!")
    }

    private fun showPile() {
        //this function is called whenever a play occur
        println("\n> PILE: $cardOnTop")
    }

    public fun start(){
        while (true){
            //Check for last card
            if(currPlayer.playerCards.size == 1){
                println("Last Card")
            }
            //Handle Card Finish Scenerio
            if(currPlayer.playerCards.isEmpty()){
                println("${currPlayer.name} has Won!")
                break
            }
            //show user his cards and he
            val userPlay:String = currPlayer.play()
            if (userPlay.equals(Statics.QUIT)){
                println("GAME OVER!")
                break
            }else if(userPlay.equals(Statics.INVALID)){
                println("Invalid Play!!")
            }else if(userPlay.equals(Statics.MARKET)){
                goMarket()
                goToNextPlayer()
                continue
            }else{
                if((userPlay.contains(cardOnTop[0])&&userPlay[1].isWhitespace()) // if number is single digit
                    || userPlay.contains(cardOnTop.substring(0,1)) //if number is 2 digit
                    || userPlay.contains(cardOnTop.substring(cardOnTop.indexOf('f',0,true) + 2))//if symbol match
                    || userPlay.contains("Whot",true)
                ){
                    cardOnTop = userPlay
                    println("${getAppropriateName()} played $userPlay\n Valid Play!!")
                    removeCardFromUserPile()
                    showPile()
                    //Perform more play checks
                    when {
                        cardOnTop.contains("1") && cardOnTop[1].isWhitespace() -> {
                            //Hold on & Suspension
                            println("Hold On!")
                            continue
                        }
                        cardOnTop.contains("2") -> {
                            //Pick two
                            goToNextPlayer()
                            for (i in 0..1){
                                goMarket()
                            }
                            goToNextPlayer()
                            continue
                        }
                        cardOnTop.contains("5") -> {
                            //Pick Three
                            goToNextPlayer()
                            for(i in 0..2){
                                goMarket()
                            }
                            goToNextPlayer()
                            continue
                        }
                        cardOnTop.contains("8") -> {
                            //Suspension
                            println("Suspension!")
                            continue
                        }
                        cardOnTop.contains("14") -> {
                            //Market
                            goToNextPlayer()
                            goMarket()
                            goToNextPlayer()
                            println("General Market!")
                            continue
                        }
                        cardOnTop.contains("whot",true) -> {
                            continue
                        }
                    }
                    goToNextPlayer()
                    continue
                }else{
                    println("${currPlayer.name} played $userPlay \na non-matching card")
                    showPile()
                    goToNextPlayer()
                    continue
                }
            }
        }
        //Show scores/Result
    }

    private fun removeCardFromUserPile() {
        val playerCards = currPlayer.playerCards
        playerCards.remove(cardOnTop)
    }

    private fun goMarket() {
        currPlayer.playerCards.add(allCards.get(0))
        allCards.removeAt(0)
        currPlayer.printPlayerCards()
        println("\n")
    }

    private fun goToNextPlayer(){
        currPlayer = if (currPlayer == player1) player2 else player1
    }

    private fun getAppropriateName(): String {
        return if(currPlayer.name.equals(player2.name)) "You" else currPlayer.name
    }


    private fun shareCard() {
        //Share the card to each player. 5 each
        for(i in 0..4) {
            //Remove from all cards pile and add to user pile
            player1.playerCards.add(allCards.get(0))
            allCards.removeAt(0)
            player2.playerCards.add(allCards.get(0))
            allCards.removeAt(0)
        }
    }
}