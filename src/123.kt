import kotlin.random.Random

class NumberGame(private val maxNumber: Int = 100) {
    private val secretNumber = (0..maxNumber).random()
    private val playerNames = arrayOf("Гравець 1", "Гравець 2", "Комп'ютер")

    fun start() {
        println("Вітаємо у грі 'Вгадай число'!")
        println("Оберіть гравця: 1 - ${playerNames[0]}, 2 - ${playerNames[1]}, 3 - ${playerNames[2]}")
        val selectedPlayer = readPlayer() ?: -1

        when (selectedPlayer) {
            1 -> playWithHuman(1, 2)
            2 -> playWithHuman(2, 1)
            3 -> playWithComputer()
            else -> println("Неправильно вибраний гравець. Оберіть гравця від 1 до 3.")
        }
    }

    private fun playWithHuman(player: Int, opponent: Int) {
        println("Гравець ${playerNames[player - 1]}, загадайте число від 0 до $maxNumber:")
        val guessedNumber = readNumber() ?: -1
        if (guessedNumber in 0..maxNumber) {
            if (guessedNumber == secretNumber) {
                println("Вітаємо, ${playerNames[player - 1]}! Ви вгадали число $secretNumber!")
            } else {
                val message = if (guessedNumber < secretNumber) "Загадане число більше." else "Загадане число менше."
                println(message)
                println("Гравець ${playerNames[opponent - 1]}, ваш хід:")
                playWithHuman(opponent, player)
            }
        } else {
            println("Неправильне введене число. Введіть число від 0 до $maxNumber.")
            println("Гравець ${playerNames[opponent - 1]}, ваш хід:")
            playWithHuman(opponent, player)
        }
    }

    private fun playWithComputer() {
        val guessedNumber = Random.nextInt(0, maxNumber + 1)
        println("Комп'ютер загадав число від 0 до $maxNumber.")
        println("Ваш хід: вгадайте число від 0 до $maxNumber:")
        val userGuess = readNumber() ?: -1
        if (userGuess in 0..maxNumber) {
            if (userGuess == secretNumber) {
                println("Вітаємо, ви вгадали число $secretNumber!")
            } else {
                val message = if (userGuess < secretNumber) "Загадане число більше." else "Загадане число менше."
                println(message)
                println("Комп'ютер вгадує:")
                playWithComputerGuess()
            }
        } else {
            println("Неправильне введене число. Введіть число від 0 до $maxNumber.")
            println("Комп'ютер вгадує:")
            playWithComputerGuess()
        }
    }

    private fun playWithComputerGuess() {
        val guessedNumber = Random.nextInt(0, maxNumber + 1)
        println("Комп'ютер вгадав число: $guessedNumber")
        if (guessedNumber == secretNumber) {
            println("Комп'ютер вгадав число $secretNumber!")
        } else {
            val message = if (guessedNumber < secretNumber) "Загадане число більше." else "Загадане число менше."
            println(message)
            playWithComputerGuess()
        }
    }

    private fun readPlayer(): Int? {
        return try {
            readLine()?.toInt()
        } catch (e: NumberFormatException) {
            null
        }
    }

    private fun readNumber(): Int? {
        return try {
            readLine()?.toInt()
        } catch (e: NumberFormatException) {
            null
        }
    }
}

fun main() {
    val game = NumberGame()
    game.start()
}
