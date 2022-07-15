package Obucjenie

class GameBoard() {
    private val board = Array(3){Array(3){' '}}


    fun startGame() {
        var end = false
        var changePlayer = false
        var stop = 0
        printBoard()
        while (!end) {
            arrangementCrossZero(changePlayer, board)
            printBoard()
            end = checkWin(end)
            if (stop == 8 && !end) {
                println("Draw")
                end = !end
            }
            changePlayer = !changePlayer
            stop++
        }
    }


    private fun printBoard() {
        println("""
        ---------
        | ${board[0][0]} ${board[0][1]} ${board[0][2]} |
        | ${board[1][0]} ${board[1][1]} ${board[1][2]} |
        | ${board[2][0]} ${board[2][1]} ${board[2][2]} |
        ---------""".trimIndent()
        )
    }

    private fun checkValue(): String {
        var truFal = false
        var value = ""
        while (!truFal) {
            print("Enter the coordinates: ")
            value = readln()
            truFal = true
            try {
                val value1 = value.substringAfter(" ").toInt()
                val value2 = value.substringBefore(" ").toInt()
            } catch (e: Exception) {
                println("You should enter numbers!")
                truFal = false
            }
        }
        return value
    }

    private fun arrangementCrossZero(change: Boolean, board: Array<Array<Char>>) {
        var value = checkValue()
        var coordinatX = value.substringBefore(' ').toInt() - 1
        var coordinatY = value.substringAfter(' ').toInt() - 1
        if (coordinatX in 0..2 && coordinatY in 0..2) {
                if (board[coordinatX][coordinatY] == ' ') {
                    if (!change) {
                        board[coordinatX][coordinatY] = 'X'
                    } else {
                        board[coordinatX][coordinatY] = 'O'
                    }
                } else {
                    println("This cell is occupied! Choose another one!")
                    arrangementCrossZero(change = change, board = board)
                }
        } else {
            println("Coordinates should be from 1 to 3!")
            arrangementCrossZero(change = change, board = board)
        }
    }

    private fun checkWin(end: Boolean): Boolean {
        var winO = false
        var winX = false

        if (board[0][0].toString() + board[0][1] + board[0][2] == "XXX") winX = true
        if (board[1][0].toString() + board[1][1] + board[1][2] == "XXX") winX = true
        if (board[2][0].toString() + board[2][1] + board[2][2] == "XXX") winX = true
        if (board[0][0].toString() + board[1][0] + board[2][0] == "XXX") winX = true
        if (board[0][1].toString() + board[1][1] + board[2][1] == "XXX") winX = true
        if (board[0][2].toString() + board[1][2] + board[2][2] == "XXX") winX = true
        if (board[0][0].toString() + board[1][1] + board[2][2] == "XXX") winX = true
        if (board[0][2].toString() + board[1][1] + board[2][0] == "XXX") winX = true

        if (board[0][0].toString() + board[0][1] + board[0][2] == "OOO") winO = true
        if (board[1][0].toString() + board[1][1] + board[1][2] == "OOO") winO = true
        if (board[2][0].toString() + board[2][1] + board[2][2] == "OOO") winO = true
        if (board[0][0].toString() + board[1][0] + board[2][0] == "OOO") winO = true
        if (board[0][1].toString() + board[1][1] + board[2][1] == "OOO") winO = true
        if (board[0][2].toString() + board[1][2] + board[2][2] == "OOO") winO = true
        if (board[0][0].toString() + board[1][1] + board[2][2] == "OOO") winO = true
        if (board[0][2].toString() + board[1][1] + board[2][0] == "OOO") winO = true


        if (winX) {
            println("X wins")
            return !end
        } else if (winO) {
            println("O wins")
            return !end
        } else return end
    }
}

fun main() {
    val board = GameBoard()
    board.startGame()
}

