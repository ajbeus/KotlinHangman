import java.util.Random
class HangmanGame {

    private val wordList = listOf("epic", "programming", "nice", "sky", "scooter")
    private val secretWord = wordList.random().lowercase()
    private var remainingAttempts = 1
    private var guessedLetters = mutableListOf<Char>()

    fun start() {
        println("Welcome to Hangman!")
        println("Please choose a difficulty:" +
                "\nFor easy, type 1." +
                "\nFor medium, type 2." +
                "\nFor hard, type 3.")
        val input = readLine()
        val difficulty = input?.toIntOrNull()
        val maxAttempts = when(difficulty){
            1 -> 10
            2 ->  6
            3 -> 4
            else -> 7
        }
        remainingAttempts = maxAttempts
        while (!isGameOver()) {
            displayWord()
            val guess = getPlayerGuess()
            processGuess(guess)
        }
        displayResult()
    }

    private fun displayWord() {
        for (letter in secretWord){
            if (guessedLetters.contains(letter)){
                print("$letter ")
            }else{
                print("_ ")
            }
        }
        println(" ")
        println("Attempts left: $remainingAttempts")
    }
    private fun getPlayerGuess(): Char {
        println("Guess a letter: ")
        val input = readLine()?.lowercase()?.getOrNull(0)
        return if (input != null && input.isLetter()) input else getPlayerGuess()
    }
    private fun processGuess(guess: Char) {
        when{
            guessedLetters.contains(guess) -> {
                println("You already guessed '$guess'. ")
            }
            !secretWord.contains(guess) -> {
                guessedLetters.add(guess)
                remainingAttempts --
                println("Wrong Guess! ")
            }
            else -> {
                guessedLetters.add(guess)
                println("Good Guess! ")
            }
        }
    }
    private fun isGameOver(): Boolean {
        val allLettersGuessed = secretWord.all {guessedLetters.contains(it) }
        return allLettersGuessed || remainingAttempts <= 0
    }
    private fun displayResult() {
        if (secretWord.all { guessedLetters.contains(it) }) {
            println("You win! The word was '$secretWord'.")
        } else {
            println("Game Over! The word was '$secretWord'. ")
        }
    }



}