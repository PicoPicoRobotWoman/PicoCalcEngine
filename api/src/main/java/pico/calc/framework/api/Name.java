package pico.calc.framework.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Declares a name for a component supported by the expression engine.
 *
 * <p>The annotation can be applied to classes. The {@link #name()} attribute defines the
 * identifier used to resolve the annotated component when processing expressions.
 *
 * <p>Example:
 * <pre>{@code
 * @Name(name = "int")
 * public final class IntLiteral implements LiteralSpec {
 *     // ...
 * }
 * }</pre>
 *
 * @see LiteralSpec
 * @since 0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Name {

    /**
     * Returns the identifier used to resolve the annotated component.
     *
     * @return the component identifier
     */
    String value();;
}
