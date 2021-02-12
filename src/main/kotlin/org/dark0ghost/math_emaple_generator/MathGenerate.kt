package org.dark0ghost.math_emaple_generator

import org.dark0ghost.math_emaple_generator.exept.VeryBigDecimalResultException
import org.dark0ghost.math_generator_example.random_object.CustomRandom
import org.dark0ghost.math_generator_example.random_object.ObjectRandom
import pl.kremblewski.expressionevaluator.evaluate
import java.math.BigDecimal
import kotlin.properties.Delegates
import kotlin.math.pow


open class MathGenerate {

    private var begin by Delegates.notNull<Int>()

    private var end by Delegates.notNull<Int>()

    private var len: Int = 0

    private var lastOperation: MathOperation = MathOperation.Plus

    private var arrayOperation: List<MathOperation> = listOf()

    private var lastNumber = 0

    private var random: CustomRandom = ObjectRandom()

    private fun generateMathExample(operation: List<MathOperation>, arrayNumber: List<Int>): String {
        var result = ""
        for (_i in 0 until len)
            result += marker(operation.randomObject(), arrayNumber.randomObject())

        if(lastOperation == MathOperation.Degree){
            result += arrayNumber.randomObject().toString()+")"
            return result
        }

        if(lastOperation != MathOperation.Division)
            result += arrayNumber.randomObject().toString()
        else
            result += getNotZeroValue()
        return result
    }

    private fun generateNumberArray(): List<Int> {
        val result = mutableListOf<Int>()
        val lenGen = if (len < 10) 10 else len

        for (_i in 0 until lenGen)
            result.add(random.randomNumber(begin, end))
        return result.shuffled()
    }

    private fun getNotZeroValue(): Int {
        var value = random.randomNumber(begin, end)
        while (value.isZero()) {
            value = random.randomNumber(begin, end)
        }
        return value
    }

    private fun getNumberForIntegerResult(): Int {
        var value = getNotZeroValue()

        if(lastNumber.isPrimeNumber)
            return value

        if(value % lastNumber > 1 )
            return lastNumber

        if(lastNumber % value <= 1){
            while(lastNumber.isIntegerResult(value))
                value++
            return value
        }
        return value
    }

    private fun getRandomOperationAndNotDivision(): MathOperation {
        var operation = arrayOperation.randomObject()
        while (operation == MathOperation.Division)
            operation = arrayOperation.randomObject()
        return operation
    }

    private fun <T : Number> isInteger(v: T): Boolean = "." !in v.toString()

    private fun marker(operation: MathOperation, number: Int): String {
        if (operation == MathOperation.Division && number.isZero()) {
            lastOperation = operation
            lastNumber = number
            return (if (number > 0) number.toString() else "($number)") + operation.operation
        }
        if (operation == MathOperation.Division && !lastNumber.isIntegerResult(number)) {
            lastOperation = operation
            lastNumber = getNumberForIntegerResult()
            return (if ( lastNumber > 0) lastNumber.toString() else "($lastNumber)") + operation.operation
        }
        if(lastOperation == MathOperation.Division && number.isZero()){
            lastOperation = operation
            lastNumber = if (!lastNumber.isZero()) getNotZeroValue() else getNumberForIntegerResult()
            return "($lastNumber)" + operation.operation
        }
        if (operation == MathOperation.Division && lastOperation == operation) {
            lastOperation = getRandomOperationAndNotDivision()
            lastNumber = number
            return (if (number > 0) number.toString() else "($number)") +  lastOperation.operation
        }
        if(operation == MathOperation.Degree){
            lastOperation = operation
            lastNumber = number
            return "($number${operation.operation}"
        }
        if(lastOperation == MathOperation.Degree){
            lastOperation = operation
            lastNumber = number
            return "$number)" + operation.operation
        }
        lastOperation = operation
        lastNumber = number
        return (if (number > 0) number.toString() else "($number)") + operation.operation
    }

    private fun getAnswerOnExample(example: String): BigDecimal = evaluate(example)

    open var customRandom: CustomRandom = ObjectRandom()
        set(value) {
            random = value
            field = value
        }

    open fun getData(operation: List<MathOperation>, begin: Int, end: Int, len: Int = 2): Pair<String, BigDecimal> {
        if(begin == end){
            val example = begin.toString() + operation.randomObject().operation + end.toString()
            val answer = try {
                getAnswerOnExample(example)
            }catch (e: ArithmeticException){
               throw VeryBigDecimalResultException("result is very big and begin and end are equal")
            }
            return example to answer

        }
        this.begin = begin
        this.end = end
        this.arrayOperation = operation.shuffled()
        this.len = len
        var mathExample: String
        var answer: BigDecimal?

        do {
            val numberArray = generateNumberArray()
            mathExample = generateMathExample(arrayOperation.shuffled(), numberArray.shuffled())
            answer = try {
                getAnswerOnExample(mathExample)
            }catch (e: Throwable){
                null
            }
        }while (answer == null || !isInteger(answer))

        return mathExample to answer
    }

    private companion object{

         fun <T> List<T>.randomObject(): T = get(ObjectRandom().randomNumber(0, lastIndex+1))

         fun Int.isZero(): Boolean = (this == 0)

         fun Int.isIntegerResult(number: Int): Boolean = (this % number) == 0

         val Int.isPrimeNumber: Boolean
             get() = 2.toDouble().pow(this) % this == 1.0
    }
}

