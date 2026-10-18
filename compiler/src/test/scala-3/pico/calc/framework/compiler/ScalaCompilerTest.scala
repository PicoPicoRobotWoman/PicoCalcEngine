package pico.calc.framework.compiler

import munit.FunSuite

class ScalaCompilerTest extends FunSuite {

  test("compiles and loads a class") {
    val clazz = ScalaCompiler.compile(
      source = """
        package generated

        class Example {
          def value: Int = 42
        }
      """,
      className = "generated.Example"
    )

    val instance = clazz.getConstructor().newInstance()
    val result = clazz.getMethod("value").invoke(instance)

    assertEquals(clazz.getName, "generated.Example")
    assertEquals(result.asInstanceOf[Int], 42)
  }

  test("rejects empty source") {
    intercept[IllegalArgumentException] {
      ScalaCompiler.compile("", "generated.Example")
    }
  }

  test("rejects blank class name") {
    intercept[IllegalArgumentException] {
      ScalaCompiler.compile("class Example", " ")
    }
  }

  test("throws CompilationError for invalid source") {
    intercept[CompilationError] {
      ScalaCompiler.compile(
        source = "class Example { def value: = }",
        className = "Example"
      )
    }
  }

}
