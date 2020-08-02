import numpy as np
import random

import loggers as lg
from settings import gameNum
if gameNum == 1:
    from tictactoe import Game
elif gameNum == 2:    
    from tictactoe3d import Game
else:
    from reverse import Game
from model import Residual_CNN

from agent import Agent, User
from provider import Provider

import config

def playMatchesBetweenVersions(env, run_version, player1version, player2version, EPISODES, logger, turns_until_tau0, goes_first = 0):
    env = Game()
    name = env.name + "{0:0>4}".format(player1version)
    if Provider.getNetByName(name) == None:
        return
    name1 = env.name + "{0:0>4}".format(player2version)
    if Provider.getNetByName(name1) == None:
        return
    if player1version == -1:
        player1 = User("user1", env.state_size, env.action_size)
    else:
        player1_NN = Residual_CNN(config.REG_CONST, config.LEARNING_RATE, env.input_shape,   env.action_size, config.HIDDEN_CNN_LAYERS)

        if player1version > 0:
            player1_network = player1_NN.read(env.name, run_version, player1version)
            player1_NN.model.set_weights(player1_network.get_weights())
        netName = env.name + "{0:0>4}".format(player1version) 
        player1 = Agent( netName, env.state_size, env.action_size, config.MCTS_SIMS, config.CPUCT, player1_NN)

    if player2version == -1:
        name = input('введите имя пользователя: ')
        user2 = Provider.getPersonByName(name)
        player2 = User(user2.name, env.state_size, env.action_size)
    else:
        player2_NN = Residual_CNN(config.REG_CONST, config.LEARNING_RATE, env.input_shape,   env.action_size, config.HIDDEN_CNN_LAYERS)
        
        if player2version > 0:
            player2_network = player2_NN.read(env.name, run_version, player2version)
            player2_NN.model.set_weights(player2_network.get_weights())
        net2Name = env.name + "{0:0>4}".format(player2version) 
        player2 = Agent(net2Name, env.state_size, env.action_size, config.MCTS_SIMS, config.CPUCT, player2_NN)

    scores, memory, points, sp_scores = playMatches(player1, player2, EPISODES, logger, turns_until_tau0, None, goes_first)

    return (scores, memory, points, sp_scores)


def playMatches(player1, player2, EPISODES, logger, turns_until_tau0, memory = None, goes_first = 0):
#     name = env.name + "{0:0>4}".format(version)
#     if Provider.getNetByName(name) == None:
#         return    
    env = Game()
    scores = {player1.name:0, "drawn": 0, player2.name:0}
    sp_scores = {'sp':0, "drawn": 0, 'nsp':0}
    points = {player1.name:[], player2.name:[]}

    for e in range(EPISODES):

        logger.info('====================')
        logger.info('EPISODE %d OF %d', e+1, EPISODES)
        logger.info('====================')

#        print (str(e+1) + ' ', end='\n')
        print (str(e+1) + ' ', end='')

        state = env.reset()
        
        done = 0
        turn = 0
        player1.mcts = None
        player2.mcts = None

        if goes_first == 0:
            player1Starts = random.randint(0,1) * 2 - 1
        else:
            player1Starts = goes_first

        if player1Starts == 1:
            players = {1:{"agent": player1, "name":player1.name}
                    , -1: {"agent": player2, "name":player2.name}
                    }
            logger.info(player1.name + ' plays as X')
        else:
            players = {1:{"agent": player2, "name":player2.name}
                    , -1: {"agent": player1, "name":player1.name}
                    }
            logger.info(player2.name + ' plays as X')
            logger.info('--------------')

        env.gameState.render(logger)
        env.gameState.render_print(logger)

        while done == 0:
            turn = turn + 1
    
            #### Run the MCTS algo and return an action
            if turn < turns_until_tau0:
                action, pi, MCTS_value, NN_value = players[state.playerTurn]['agent'].act(state, 1)
            else:
                action, pi, MCTS_value, NN_value = players[state.playerTurn]['agent'].act(state, 0)

            if memory != None:
                ####Commit the move to memory
                memory.commit_stmemory(env.identities, state, pi)


            logger.info('action: %d', action)
            for r in range(env.grid_shape[0]):
                logger.info(['----' if x == 0 else '{0:.2f}'.format(np.round(x,2)) for x in pi[env.grid_shape[1]*r : (env.grid_shape[1]*r + env.grid_shape[1])]])
            logger.info('MCTS perceived value for %s: %f', state.pieces[str(state.playerTurn)] ,np.round(MCTS_value,2))
            logger.info('NN perceived value for %s: %f', state.pieces[str(state.playerTurn)] ,np.round(NN_value,2))
            logger.info('====================')

            ### Do the action
            state, value, done, _ = env.step(action) #the value of the newState from the POV of the new playerTurn i.e. -1 if the previous player played a winning move
            
            env.gameState.render(logger)
            env.gameState.render_print(logger)
            import datetime 
            now = datetime.datetime.now()
            gameName = env.name
            if done == 1: 
                if memory != None:
                    #### If the game is finished, assign the values correctly to the game moves
                    for move in memory.stmemory:
                        if move['playerTurn'] == state.playerTurn:
                            move['value'] = value
                        else:
                            move['value'] = -value
                         
                    memory.commit_ltmemory()
                if value == 1:
                    logger.info('%s WINS!', players[state.playerTurn]['name'])
                    print('WINS!', players[state.playerTurn]['name'])
                    if player2.name.find(gameName) > -1:
                        if players[state.playerTurn]['name'] == player1.name:
                            Provider.addCompet(player1.name, player2.name, gameName,now, 1 )
                        if players[state.playerTurn]['name'] == player2.name:
                            Provider.addCompet(player1.name, player2.name, gameName,now, 2 )
                    else:
                        if players[state.playerTurn]['name'] == player1.name:
                            Provider.addCompetWithUser(player1.name, player2.name, gameName,now, 1)
                        if players[state.playerTurn]['name'] == player2.name:
                            Provider.addCompetWithUser(player1.name, player2.name, gameName,now, 2 )
                    scores[players[state.playerTurn]['name']] = scores[players[state.playerTurn]['name']] + 1
                    
                    if state.playerTurn == 1: 
                        sp_scores['sp'] = sp_scores['sp'] + 1
                        
                    else:
                        sp_scores['nsp'] = sp_scores['nsp'] + 1

                elif value == -1:
                    logger.info('%s WINS!', players[-state.playerTurn]['name'])
                    print(' WINS!', players[-state.playerTurn]['name'])
                    if player2.name.find(gameName) > -1:
                        if players[-state.playerTurn]['name'] == player1.name:
                            Provider.addCompet(player1.name, player2.name, gameName,now, 1 )
                        if players[-state.playerTurn]['name'] == player2.name:
                            Provider.addCompet(player1.name, player2.name, gameName,now, 2 )
                    else:
                        if players[-state.playerTurn]['name'] == player1.name:
                            Provider.addCompetWithUser(player1.name, player2.name, gameName,now, 1 )
                        if players[-state.playerTurn]['name'] == player2.name:
                            Provider.addCompetWithUser(player1.name, player2.name, gameName,now, 2 )
                    scores[players[-state.playerTurn]['name']] = scores[players[-state.playerTurn]['name']] + 1
               
                    if state.playerTurn == 1: 
                        sp_scores['nsp'] = sp_scores['nsp'] + 1
                    else:
                        sp_scores['sp'] = sp_scores['sp'] + 1

                else:
                    logger.info('DRAW...')
                    print('DRAW...')
                    Provider.addCompet(player1.name, player2.name, gameName,now, 0 )
                    scores['drawn'] = scores['drawn'] + 1
                    sp_scores['drawn'] = sp_scores['drawn'] + 1

                pts = state.score
                points[players[state.playerTurn]['name']].append(pts[0])
                points[players[-state.playerTurn]['name']].append(pts[1])

    return (scores, memory, points, sp_scores)
