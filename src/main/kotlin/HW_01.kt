import kotlin.reflect.full.declaredMemberFunctions

interface TestRunner { fun <T> runTest(steps: T, test: () -> Unit) }
class HM_01(): TestRunner {
    override fun <T> runTest(steps: T, test: () -> Unit) {
        var bafore = steps!!::class.declaredMemberFunctions
            .filter { it.name.startsWith("before") }
            .forEach({
                print("call ${it.name} ")
                it.call(steps)
            })


        var after = steps!!::class.declaredMemberFunctions
            .filter { it.name.startsWith("after") }
            .forEach({
                print("call ${it.name} ")
                it.call(steps)
            })

    }
}
fun main() {
    var x1 = HM_01()
    x1.runTest(SomeClassTest()) { println("Start") }
}
class SomeClassTest(){
    fun beforeOne(){
        println("1")
    }
    fun beforeTwo(){
        println("2")

    }
    fun before3(){
        println("3")

    }
    fun afterFirst(){
        println("11")
    }
    fun afterSecond(){
        println("22")
    }
}


