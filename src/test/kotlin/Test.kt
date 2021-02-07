import org.dark0ghost.math_emaple_generator.MathGenerate
import org.dark0ghost.math_emaple_generator.MathOperation
import org.dark0ghost.math_emaple_generator.exept.VeryBigDecimalResultException
import org.junit.jupiter.api.Test
import java.lang.Exception

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
      val (example, _) = mathGenerate.getData(MathOperation.Division.toList(),-100000,100000,1)
    }

    @Test
    fun equalBeginAndEnd() {
        try {
            mathGenerate.getData(MathOperation.Degree.toList(), 1000000000, 1000000000, 1)
        }catch (e: VeryBigDecimalResultException){
            return
        }
        throw Exception()
    }
}