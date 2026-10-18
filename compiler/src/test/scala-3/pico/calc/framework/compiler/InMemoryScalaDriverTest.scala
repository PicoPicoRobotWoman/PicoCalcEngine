package pico.calc.framework.compiler

import dotty.tools.io.VirtualDirectory
import munit.FunSuite

class InMemoryScalaDriverTest extends FunSuite {

  private val classpath =
    System.getProperty("java.class.path")

  test("compiles valid Scala source in memory") {
    val output = InMemoryScalaDriver.compile(
      source = """
        object Generated {
          def value: Int = 42
        }
      """,
      classpath = classpath
    )

    assert(output.iterator.nonEmpty)
  }

  test("stores compiled class files in memory") {
    val output = InMemoryScalaDriver.compile(
      source = """
        class Generated {
          def value: Int = 42
        }
      """,
      classpath = classpath
    )

    def containsClass(directory: VirtualDirectory): Boolean =
      directory.iterator.exists {
        case subdirectory: VirtualDirectory =>
          containsClass(subdirectory)
        case file =>
          file.name.endsWith(".class")
      }

    assert(containsClass(output))
  }

  test("throws CompilationError for invalid Scala source") {
    intercept[CompilationError] {
      InMemoryScalaDriver.compile(
        source = "class { invalid syntax",
        classpath = classpath
      )
    }
  }

  test("throws CompilationError when compiler initialization fails") {
    intercept[CompilationError] {
      InMemoryScalaDriver.compile(
        source = "object Generated",
        classpath = ""
      )
    }
  }
}
