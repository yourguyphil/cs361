package Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestCommands.class,
	TestIND.class,
	TestInit.class,
	TestParser.class,
	TestTime.class})

public class TestSuite {
}
