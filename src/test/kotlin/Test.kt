import org.dark0ghost.math_emaple_generator.MathGenerate
import org.dark0ghost.math_emaple_generator.MathOperation
import org.junit.jupiter.api.Test

class TestClass {

    @Test
    fun test(){
     val regex: Regex = """(\d+)\^(\d+)""".toRegex()

     val mathGenerate = MathGenerate()
     val data = mathGenerate.getData(MathOperation.Degree.toList(),20,99,1)

     assert(regex.find(data.first) != null)
    }

}