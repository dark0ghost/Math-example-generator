### Math example  generator 
[![GitHub issues](https://img.shields.io/github/issues/dark0ghost/androidcustomcamera)](https://github.com/dark0ghost/androidcustomcamera/issues)
[![GitHub release](https://img.shields.io/github/release/dark0ghost/androidcustomcamera)](https://github.com/dark0ghost/androidcustomcamera/releases/)
[![Github all releases](https://img.shields.io/github/downloads/dark0ghost/androidcustomcamera/total.svg)](https://github.com/dark0ghost/androidcustomcamera/releases/)
[![GitHub code size](https://img.shields.io/github/languages/code-size/dark0ghost/androidcustomcamera?style=flat)](https://github.com/dark0ghost/androidcustomcamera)
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