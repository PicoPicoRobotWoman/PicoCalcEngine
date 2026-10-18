package pico.calc.framework.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Declares metadata for a constant specification.
 *
 * <p>The annotation is available at runtime and can be applied to types.
 * The {@link #name()} attribute defines the name used to identify the constant.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConstantMeta {

    /**
     * Returns the name used to identify the constant.
     *
     * @return the constant name
     */
    String name();
}
