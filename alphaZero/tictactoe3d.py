import numpy as np
import logging


class Game:

    def __init__(self):
        self.currentPlayer = 1
        self.gameState = GameState(np.array(
            np.zeros(27), dtype=np.int), 1)
        self.actionSpace = np.array(
            np.zeros(27), dtype=np.int)
        self.pieces = {'1': 'X', '0': '-', '-1': 'O'}
        self.grid_shape = (3, 9)
        self.input_shape = (2, 3, 9)
        self.name = 'tictactoe3d'
        self.state_size = len(self.gameState.binary)
        self.action_size = len(self.actionSpace)

    def reset(self):
        self.gameState = GameState(np.array([0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0], dtype=np.int), 1)
        self.currentPlayer = 1
        return self.gameState

    def step(self, action):
        next_state, value, done = self.gameState.takeAction(action)
        self.gameState = next_state
        self.currentPlayer = -self.currentPlayer
        info = None
        return ((next_state, value, done, info))

    def identities(self, state, actionValues):
        identities = [(state, actionValues)]

        currentBoard = state.board
        currentAV = actionValues

        currentBoard = np.array([
            
            currentBoard[2], currentBoard[1], currentBoard[0],
            currentBoard[5], currentBoard[4], currentBoard[3],
            currentBoard[8], currentBoard[7], currentBoard[6],
            
            currentBoard[11], currentBoard[10], currentBoard[9],
            currentBoard[14], currentBoard[13], currentBoard[12],
            currentBoard[17], currentBoard[16], currentBoard[15],
            
            currentBoard[20], currentBoard[19], currentBoard[18],
            currentBoard[23], currentBoard[22], currentBoard[21],
            currentBoard[26], currentBoard[25], currentBoard[24]
            
            
        ])

        currentAV = np.array([
            
            currentAV[2], currentAV[1], currentAV[0],
             currentAV[5], currentAV[4], currentAV[3],
             currentAV[8], currentAV[7], currentAV[6],
            
            currentAV[11], currentAV[10], currentAV[9],
             currentAV[14], currentAV[13], currentAV[12],
             currentAV[17], currentAV[16], currentAV[15],
            
            currentAV[20], currentAV[19], currentAV[18],
             currentAV[23], currentAV[22], currentAV[21],
             currentAV[26], currentAV[25], currentAV[24],
            
        ])

        identities.append((GameState(currentBoard, state.playerTurn), currentAV))

        return identities


class GameState():
    def __init__(self, board, playerTurn):
        self.board = board
        self.pieces = {'1': 'X', '0': '-', '-1': 'O'}
        self.winners = [
            [0, 1, 2],
            [3, 4, 5],
            [6, 7, 8],

            [0, 3, 6],
            [1, 4, 7],
            [2, 5, 8],

            [0, 4, 8],
            [2, 4, 6],
            
            [9, 10, 11],
            [12, 13, 14],
            [15, 16, 17],
            
            [9, 12, 15],
            [10, 13, 16],
            [11, 14, 17],
            
            [11, 13, 15],
            [9, 13, 17],
            
            [18, 19, 20],
            [21, 22, 23],
            [24, 25, 26],
            
            [18, 21, 24],
            [19, 22, 25],
            [20, 23, 26],
            
            [18, 22, 26],
            [20, 22, 24],

            [6, 7, 8],
            [15, 16, 17],
            [24, 25, 26],
            
            [6, 15, 24],
            [7, 16, 25], 
            [8, 17, 26],
            
            [6, 16, 26],
            [8, 16, 24],
            
         
            [3, 4, 5],
            [12, 13, 14],
            [21, 22, 23],
            
            [3, 12, 21],
            [4, 13, 22],
            [5, 14, 23],
            
            [3, 13, 23],
            [5, 13, 21],
        

            [0, 1, 2],
            [9, 10, 11],
            [18, 19, 20],
            
            [0, 9, 18],
            [1, 10, 19],
            [2, 11, 20],
            
            [0, 10, 20],
            [2, 10, 18],
            
            
            
            
            [0, 3, 6],
            [9, 12, 15],
            [18, 21, 24],
            
            [0, 9, 18],
            [3, 12, 21],
            [6, 15, 24],
            
            [0, 12, 24],
            [6, 12, 18],
            

            [1, 4, 7],
            [10, 13, 16],
            [19, 22, 25],
            
            
            [1, 10, 19],
            [4, 13, 22],
            [7, 16, 25],
            
            [1, 13, 25],
            [7, 13, 19],
            
            [2, 5, 8],
            [11, 14, 17],
            [20, 23, 26],
            
            [2, 11, 20],
            [5, 14, 23],
            [8, 17, 26], 
            
            [2, 14, 26],
            [8, 14, 20],
            
            [8, 13, 18],
            [0, 13, 26],
            [6, 13, 20],
            [2, 13, 24]
        ]
        self.playerTurn = playerTurn
        self.binary = self._binary()
        self.id = self._convertStateToId()
        self.allowedActions = self._allowedActions()
        self.isEndGame = self._checkForEndGame()
        self.value = self._getValue()
        self.score = self._getScore()

    def _allowedActions(self):
        # Создание списка допустимых ходов
        allowed = []
        for i in range(len(self.board)):
            if i >= len(self.board) - 9:
                if self.board[i]==0:
                    allowed.append(i)
            else:
                if self.board[i] == 0 and self.board[i+9] != 0:
                    allowed.append(i)

        return allowed

    def _binary(self):
        # Выводит np.aarray длины 2*len(self.board), состоящий из нулей и единиц
        # первая половина полей: единицы соответсвуют полям занятым первым игроком
        # вторая половина полей: единицы соответсвуют полям занятым вторым игроком
        currentplayer_position = np.zeros(len(self.board), dtype=np.int)
        currentplayer_position[self.board == self.playerTurn] = 1

        other_position = np.zeros(len(self.board), dtype=np.int)
        other_position[self.board == -self.playerTurn] = 1

        position = np.append(currentplayer_position, other_position)

        return (position)

    def _convertStateToId(self):
        # Выводит строку длины 2*len(self.board), состоящий из нулей и единиц
        # первая половина полей: единицы соответсвуют полям занятым первым игроком
        # вторая половина полей: единицы соответсвуют полям занятым вторым игроком
        player1_position = np.zeros(len(self.board), dtype=np.int)
        player1_position[self.board == 1] = 1

        other_position = np.zeros(len(self.board), dtype=np.int)
        other_position[self.board == -1] = 1

        position = np.append(player1_position, other_position)

        id = ''.join(map(str, position))

        return id

    def _checkForEndGame(self):
        # Проверка на конец игры. Выводит 1, если конец игры, иначе 0.
        if np.count_nonzero(self.board) == 27:
            return 1

        for x, y, z in self.winners:
            if (self.board[x] + self.board[y] + self.board[z] == 3* -self.playerTurn):
                return 1
        return 0

    def _getValue(self):
        # Перевод: Это значение состояния для текущего игрока, т. е. если предыдущий игрок сыграл выигрышный ход, вы проигрываете
        # This is the value of the state for the current player
        # i.e. if the previous player played a winning move, you lose
        # Если предыдущим ходом игрок выиграл выводит (-1, -1, 1) иначе (0, 0, 0)
        for x, y, z in self.winners:
            if (self.board[x] + self.board[y] + self.board[z] == 3 * -self.playerTurn):
                return (-1, -1, 1)
        return (0, 0, 0)

    def _getScore(self):
        # Выводит выдодит первое и втрое значение из value (_getValue)
        tmp = self.value
        return (tmp[1], tmp[2])

    def takeAction(self, action):
        # Переход в новое состояние
        # Выводит новую позицию, value, идентификатор конца игры
        newBoard = np.array(self.board)
        newBoard[action] = self.playerTurn

        newState = GameState(newBoard, -self.playerTurn)

        value = 0
        done = 0

        if newState.isEndGame:
            value = newState.value[0]
            done = 1

        return (newState, value, done)

    def render(self, logger):
        # Запись позиции в лог
        for r in range(9):
            logger.info([self.pieces[str(x)] for x in self.board[3*r : (3*r + 3)]])
        logger.info('--------------')

    def render_print(self, logger):        # Вывод позиции на экран
        for r in range(9):
            print([self.pieces[str(x)] for x in self.board[3*r : (3*r + 3)]])
            if r%3 == 2:
                print('===============')
        print('--------------')
        