import org.dark0ghost.math_emaple_generator.MathGenerate
import org.dark0ghost.math_emaple_generator.MathOperation
import org.junit.jupiter.api.Test

class TestClass {
    private val mathGenerate = MathGenerate()

    @Test
    fun testDegree(){
     val regex: Regex = """((\d+)\^(\d+))""".toRegex()
     val data = mathGenerate.getData(MathOperation.Degree.toList(),20,99,1)

     assert(regex.find(data.first) != null)
    }

    @Test
    fun testZeroDivision(){
        mathGenerate.getData(MathOperation.Division.toList(),0,20,1)
    }

    @Test
    fun testDivision(){
      val (example, _) = mathGenerate.getData(MathOperation.Division.toList(),-100000,100000,2)

    }
}