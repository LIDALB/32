import coordinates.System
import org.openrndr.math.Vector2

data class GridPosition(val x: Int, val y: Int) {
    companion object {
        fun from(xy: Vector2): GridPosition =
            GridPosition(xy.x.toInt(), xy.y.toInt())
    }

    fun coord(system: System) =
        system.coord(Vector2(x.toDouble(), y.toDouble()))

    fun neighbor(direction: Direction): GridPosition {
        val xOffset = when (direction.angle45) {
            0 -> 1
            1 -> 1
            2 -> 0
            3 -> -1
            4 -> -1
            5 -> -1
            6 -> 0
            7 -> 1
            else -> throw RuntimeException("Invalid angle")
        }
        val yOffset = when (direction.angle45) {
            0 -> 0
            1 -> -1
            2 -> -1
            3 -> -1
            4 -> 0
            5 -> 1
            6 -> 1
            7 -> 1
            else -> throw RuntimeException("Invalid angle")
        }
        return GridPosition(x + xOffset, y + yOffset)
    }
}

data class Path(val positions: MutableList<GridPosition>)

