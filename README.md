### Math example  generator 
[![GitHub issues](https://img.shields.io/github/issues/dark0ghost/Math-example-generator)](https://github.com/dark0ghost/Math-example-generator/issues)
[![GitHub release](https://img.shields.io/github/release/ddark0ghost/Math-example-generator)](https://github.com/dark0ghost/Math-example-generator/releases/)
[![Github all releases](https://img.shields.io/github/downloads/dark0ghost/Math-example-generator/total.svg)](https://github.com/dark0ghost/Math-example-generator/releases/)
[![GitHub code size](https://img.shields.io/github/languages/code-size/dark0ghost/Math-example-generator?style=flat)](https://github.com/ddark0ghost/Math-example-generator)
[![](https://jitpack.io/v/imkarl/Badge.svg)](https://jitpack.io/#imkarl/Badge)

This project generates on custom random math examples in a given range with a given length and operations

## Use in gradle
```groovy
repositories {
    
maven { 
    url 'https://jitpack.io'
}
}

dependencies {
    
implementation 'com.github.cregus'
    
}
```

## Use in code

```kotlin
fun main(){
 val ex = MathGenerate()
 val operation: List<MathOperation> = listof(MathOperation.Division,MathOperation.Plus)  // math operation use in example 
 val len = 1 // len example   
 val result:  Pair<String, BigDecimal> = ex.getData(operation,10,100,len)  
 println("${result.first}=${result.second}")
}
```

## use castom random

```kotlin
class UserRandom:  CustomRandom{
    override fun randomNumber(begin: Int,end: Int): Int{
        //your implements
    }
}

fun main(){
 val ex = MathGenerate()
 val operation: List<MathOperation> = listof(MathOperation.Division,MathOperation.Plus)  // math operation use in example 
 val len = 1 // len example 
 ex.customRandom = UserRandom   
 val result:  Pair<String, BigDecimal> = ex.getData(operation,10,100,len)  
 println("${result.first}=${result.second}")
}
```