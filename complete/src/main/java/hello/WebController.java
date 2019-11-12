package hello;

import javax.validation.Valid;
import javax.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class WebController implements WebMvcConfigurer {
  // http://www.slf4j.org/faq.html#declared_static
  private static final Logger log = LogManager.getLogger(WebController.class);


  @Autowired
  private Validator validator;

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/results").setViewName("results");
  }

  @GetMapping("/")
  public String showForm(PersonForm personForm) {
    return "form.html";
  }

  @PostMapping("/")
  public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
    log.info("checkPersonInfo handler: " + personForm.getName() );
    
    if (bindingResult.hasErrors()) {
      return "form.html";
    }

    return "redirect:/results.html";
  }
}
