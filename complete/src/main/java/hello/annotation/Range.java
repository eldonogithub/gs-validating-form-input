package hello.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import hello.validator.RangeValidator;

/**
 * Mark an Age constrained to a minimum and maximum value
 *
 */
@Min(18)
@Max(110)
@ReportAsSingleViolation
@Constraint(validatedBy = RangeValidator.class)

@Retention(RUNTIME)
@Target({ TYPE, FIELD })
@Documented
public @interface Range {

  // JSR-303
  // The annotation element names message, groups and payload are
  // considered reserved names; annotation elements starting with valid are not
  // allowed; a constraint may use any other element name for its attributes.

  // Every constraint annotation must define a message element of type String.
  String message() default "{ca.blackperl.constraint.Age.message}";
  // The message element value is used to create the error message. See Section
  // 4.3 for a detailed explanation. It is recommended to default message values
  // to resource bundle keys to enable internationalization.

  // Every constraint annotation must define a groups element that specifies the
  // processing groups with which the constraint declaration is associated.
  Class<?>[] groups() default {}; // The default value must be an empty array.
  // Groups are typically used to control the order in which constraints are
  // evaluated, or to perform validation of the partial state of a JavaBean.

  // Constraint annotations must define a payload element that specifies the
  // payload with which the the constraint declaration is associated.
  Class<? extends Payload>[] payload() default {}; // The default value must be
                                                   // an empty array.

  @OverridesAttribute(constraint = Min.class, name = "value")
  long min();

  @OverridesAttribute(constraint = Max.class, name = "value")
  long max();

  @ReportAsSingleViolation
  @Target({ TYPE, FIELD })
  @Retention(RUNTIME)
  @Documented
  @interface List {
    Range[] value();
  }

}
