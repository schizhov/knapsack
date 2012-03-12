/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.ukp.io.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import mz.ukp.io.Campaign;
import mz.ukp.io.Input;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author stanislav.chizhov
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(FileUtils.class)
public class FileInputProviderTest {
    
    private final List<String> oneLine = Arrays.asList("123");
    private final List<String> minimal = Arrays.asList("123","a,2,3");
    private final Input minimalInput = new Input(123,Arrays.asList(new Campaign("a", 2, 3)));;
            
    private File file;
    
    private FileInputProvider provider;
    
    @Before
    public void setUp() {
        file = mock(File.class);
        provider = new FileInputProvider(file);
    }
    
    @Test
    public void createsInputForCorrectFile() throws IOException {
        PowerMockito.mockStatic(FileUtils.class);
        when(FileUtils.readLines(file)).thenReturn(minimal);
        assertEquals(minimalInput, provider.getInput());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void failsWhen1LineOnly() throws IOException {
        PowerMockito.mockStatic(FileUtils.class);
        when(FileUtils.readLines(file)).thenReturn(oneLine);
        assertEquals(minimalInput, provider.getInput());
    }
}
