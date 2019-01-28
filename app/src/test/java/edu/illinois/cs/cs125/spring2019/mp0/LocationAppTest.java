package edu.illinois.cs.cs125.spring2019.mp0;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import edu.illinois.cs.cs125.spring2019.mp0.lib.Locator;

@RunWith(RobolectricTestRunner.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest({Locator.class})
public class LocationAppTest {
    private MainActivity mainActivity;

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Before
    public void setupActivity() {
        PowerMockito.mockStatic(Locator.class);
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    // Robolectric tests are slow, but most of the delay is in setupActivity above.
    @Test(timeout=1000)
    public void centerButtonTest() {
        Assert.assertFalse(mainActivity.centered);
        mainActivity.processNewLocation(10.0, 12.8);
        Assert.assertTrue("button handler not attached", mainActivity.findViewById(R.id.center).performClick());
        Assert.assertTrue(mainActivity.centered);
    }
}
