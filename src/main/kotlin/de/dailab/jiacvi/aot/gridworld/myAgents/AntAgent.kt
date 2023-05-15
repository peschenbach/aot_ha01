package de.dailab.jiacvi.aot.gridworld.myAgents

import de.dailab.jiacvi.Agent
import de.dailab.jiacvi.aot.gridworld.*
import de.dailab.jiacvi.behaviour.act

/**
 * Stub for your AntAgent
 * */
class AntAgent(antId: String): Agent(overrideName=antId) {
    // TODO you might need to put some variables to save stuff here
    var antPosition = Position(0,0)



    override fun behaviour() = act {
        /* TODO here belongs most of your agents logic.
        *   - Check the readme "Reactive Behaviour" part and see the Server for some examples
        *   - try to make a move in the gridworld
        *   - build your ant algorithm by communicating with your environment when looking for the way
        *   - adjust your parameters to get better results
        */

        on<PosToAnt> {it ->
            antPosition = it.position
            print("ANT POSITION: " + antPosition)
        }

    }
}