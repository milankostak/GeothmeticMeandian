import kotlin.math.abs
import kotlin.math.pow
// https://xkcd.com/2435/
// https://math.stackexchange.com/questions/4057261/for-what-values-does-the-geothmetic-meandian-converge
fun main() {
    var list = listOf(1.0, 1.0, 2.0, 3.0, 5.0)
    val result: Double
    val epsilon = 0.0000001

    while (true) {
        val arithmeticMean = list.average()
        val geometricMean = list.reduce { n1, n2 -> n1 * n2 }.pow(1.0 / list.size)
        val median = list.median()

        println(arithmeticMean)
        println(geometricMean)
        println(median)
        println()

        if (arithmeticMean.isNaN() || geometricMean.isNaN() || median.isNaN()) {
            result = Double.NaN
            break
        }

        list = listOf(arithmeticMean, geometricMean, median)

        if ((abs(arithmeticMean - geometricMean) < epsilon && abs(geometricMean - median) < epsilon)) {
            result = list.median()
            break
        }
    }

    println(result)
}

fun List<Double>.median(): Double {
    val size = this.size
    return this.stream().mapToDouble { value -> value.toDouble() }
            .sorted()
            .skip(((size - 1) / 2).toLong())
            .limit((2 - size % 2).toLong())
            .average()
            .orElse(Double.NaN)
}
