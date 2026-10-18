package pico.calc.framework.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Declares metadata for a function supported by the expression engine.
 *
 * <p>The annotation is applied to function specification classes.
 * Its {@link #name()} attribute defines the function identifier
 * used during function resolution.
 *
 * <p>Example:
 * <pre>{@code
 * @FunctionMeta(name = "sum")
 * public final class SumFunction implements FunctionSpec {
 *     // ...
 * }
 * }</pre>
 *
 * @see FunctionSpec
 * @since 0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FunctionMeta {

    /**
     * Returns the identifier used to resolve the function.
     *
     * @return the function identifier
     */
    String name();
}
