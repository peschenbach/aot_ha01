package de.dailab.jiacvi.aot.gridworld.myAgents

import de.dailab.jiacvi.aot.gridworld.Position

/** tell ants there initial position */
data class PosToAnt(val position: Position)

/** tell ants the next turn starts */
data class TurnToAnt(val turn: Int)