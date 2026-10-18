package pico.calc.framework.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Declares metadata for a literal supported by the expression engine.
 *
 * <p>The annotation is applied to classes implementing {@code LiteralSpec}.
 * The {@link #name()} attribute defines the identifier used to resolve
 * the literal when parsing and evaluating expressions.
 *
 * <p>Example:
 * <pre>{@code
 * @LiteralMeta(name = "int")
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
public @interface LiteralMeta {

    /**
     * Returns the identifier used to resolve this literal.
     *
     * @return the literal identifier
     */
    String name();
}
