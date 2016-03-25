package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.chronotimer.TestChannel;
import test.chronotimer.TestTime;
import test.io.TestParser;
import test.race.TestIND;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestChannel.class,
	TestCommands.class,
	TestIND.class,
	TestInit.class,
	TestParser.class,
	TestTime.class})

public class TestSuite {
}
