package inciManager.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	
	SimpleTestNotification.class,
	SimpleTestOperator.class,
	SimpleTestsIncidence.class,
})
public class AllDomainTests { }
