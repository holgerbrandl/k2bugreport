import org.kalasim.Component
import org.kalasim.ComponentGenerator
import org.kalasim.Resource
import org.kalasim.createSimulation
import org.kalasim.dependency
import org.kalasim.exponential
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

class PetrolStation(numStations: Int) : Resource(capacity = numStations)


class Vehicle() : Component() {

    override fun process() = sequence<Component> {

        hold(5.minutes, "driving")

        val petrolStation = get<PetrolStation>()

        request(petrolStation) {
            hold(3.minutes, "refilling")
        }
    }
}

fun main() {
    val sim = createSimulation(enableComponentLogger = true) {

        dependency { PetrolStation(4) }

        // implement arrival generator
        ComponentGenerator(exponential(3.minutes)) { Vehicle() }
    }

    sim.run(3.hours)
}