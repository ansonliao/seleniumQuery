package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.PSEUDO_CLASS_VALUE_NOT_USED;
import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.SELECTOR_NOT_USED;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

/**
 * http://api.jquery.com/reset-selector/
 * 
 * @since 1.0.0
 * @author acdcjunior
 */
public class ResetPseudoClass implements PseudoClass {
	
	private static final ResetPseudoClass instance = new ResetPseudoClass();
	public static ResetPseudoClass getInstance() {
		return instance;
	}
	private ResetPseudoClass() { }
	
	private static final String RESET = "reset";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return RESET.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return ("input".equals(element.getTagName()) || "button".equals(element.getTagName()))
				&& RESET.equalsIgnoreCase(element.getAttribute("type"));
	}
	
	private static final SqCSSFilter resetPseudoClassFilter = new PseudoClassFilter(getInstance(),
																		SELECTOR_NOT_USED, PSEUDO_CLASS_VALUE_NOT_USED);
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		// :reset is an extension selector, nobody implements it natively
		return CompiledSelector.createFilterOnlySelector(resetPseudoClassFilter);
	}
	
}