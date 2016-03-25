package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.chronotimer.TestChannel;
import test.chronotimer.TestChronoTimer;
import test.chronotimer.TestTime;
import test.io.TestCommand;
import test.io.TestParser;
import test.io.TestWriter;
import test.race.TestAbstractEvent;
import test.race.TestIND;
import test.race.TestPARIND;
import test.race.TestRacer;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestChannel.class,
	TestChronoTimer.class,
	TestTime.class,
	
	TestCommand.class,
	TestParser.class,
	TestWriter.class,
	
	TestAbstractEvent.class,
	TestIND.class,
	TestPARIND.class,
	TestRacer.class,
	
	TestCommands.class,
	TestInit.class})

public class TestSuite {
}
