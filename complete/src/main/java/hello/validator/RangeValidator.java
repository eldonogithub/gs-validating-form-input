package hello.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hello.annotation.Range;

public class RangeValidator implements ConstraintValidator<Range, String> {

  // http://www.slf4j.org/faq.html#declared_static
  private static final Logger log = LogManager.getLogger(RangeValidator.class);

  public void initialize(Range constraint) {
    log.info("initializing AgeValidator: ");
  };

  // While not mandatory, it is considered a good practice to split the core
  // constraint validation from the not null constraint validation (for example,
  // an @Email constraint will return true on a null object, i.e. will not take
  // care of the @NotNull validation) null
  // can have multiple meanings but is commonly used to express that a value
  // does not make sense, is not available or is simply unknown.

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    log.info("checking if it is valid: " + value);

    // Don't handle the null case following best practice as defined above
    if (value == null) {
      log.info("value is null - return true");
      return true;
    }

    // If we got here, we probably passed the other composite constraints
    log.info("value not null - return true");
    return true;
  }

}
