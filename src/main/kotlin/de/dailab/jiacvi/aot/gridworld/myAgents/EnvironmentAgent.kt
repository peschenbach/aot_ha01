package de.dailab.jiacvi.aot.gridworld.myAgents

import de.dailab.jiacvi.Agent
import de.dailab.jiacvi.aot.gridworld.*
import de.dailab.jiacvi.behaviour.act
import java.time.Duration
import java.util.*



/**
 * Stub for your EnvironmentAgent
 * */
class EnvironmentAgent(private val envId: String): Agent(overrideName=envId) {
    // TODO you might need to put some variables to save stuff here
    var antIDs = listOf<String>("1","2","3")
    var gridSize = Position(0,0)
    var nestPosition = Position(0,0)
    var obstacleList: List<Position>? = null


    override fun preStart() {
        // TODO if you want you can do something once before the normal lifecycle of your agent
        super.preStart()

        /** send start game msg to server */
        val ref = system.resolve(SERVER_NAME)
        ref invoke ask<StartGameResponse>(StartGameMessage(envId,antIDs)) { res ->
            this@EnvironmentAgent.gridSize = res.size
            nestPosition = res.nestPosition
            obstacleList = res.obstacles
            print("gridsize PRE: " + gridSize)

            for (a in antIDs) {
                val ref = system.resolve(a)
                print("ref PRE: " + ref)
                ref tell PosToAnt(res.nestPosition)
            }

        }
    }

    override fun behaviour() = act {
        /* TODO here belongs most of your agents logic.
        *   - Check the readme "Reactive Behaviour" part and see the Server for some examples
        *   - try to start a game with the StartGameMessage
        *   - you need to initialize your ants, they don't know where they start
        *   - here you should manage the pheromones dropped by your ants
        *   - REMEMBER: pheromones should transpire, so old routes get lost
        *   - adjust your parameters to get better results, i.e. amount of ants (capped at 40)
        */

        /** create ant agents */
        for (a in antIDs) {
            print(a)
            system.spawnAgent(AntAgent(a))
        }

        /** listen for next turn */
        listen<GameTurnInform>(BROADCAST_TOPIC) {it ->
            print("TURN: " + it.gameTurn)

            for (a in antIDs) {
                val ref = system.resolve(a)
                print("ref: " + ref)
                ref tell "antTest"
            }
        }


        print("gridsize behave: " + gridSize)
        print("nest behave: " + nestPosition)

    }
}