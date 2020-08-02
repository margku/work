run_folder = './run/'
run_archive_folder = './run/'
gameNum = int(input('select the type of game: \n 1-tic-tac-toe \n 2-tic-tac-toe 3d \n 3-reverse \n' ))
# from settings import gameNum
if gameNum == 1:
    from tictactoe import Game
elif gameNum == 2:    
    from tictactoe3d import Game
else:
    from reverse import Game
env = Game() 