package mz.ukp.io.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mz.ukp.io.Campaign;
import mz.ukp.io.Input;
import mz.ukp.io.InputProvider;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.UnhandledException;
import org.apache.commons.lang.Validate;

/**
 * Provides <tt>Input</tt> from file.
 * 
 * @author stanislav.chizhov
 */
public class FileInputProvider implements InputProvider {

    private final File file;

    public FileInputProvider(File file) {
        Validate.notNull(file);
        this.file = file;
    }
    
    private List<String> readLines() {
        try {
            return FileUtils.readLines(file);
        } catch (IOException ex) {
            throw new UnhandledException("Failed to read file "+file.getAbsolutePath(),ex);
        }
    }
    
    /**
     * All amounts (campaign sizes and prices) should be positive.
     * Otherwise <tt>IllegalArgumentException</tt> will be raised.
     * NOTE: package protected for the sake of testing
     */
    static int parseAmount(String amount) throws IllegalArgumentException {
        int value = Integer.valueOf(StringUtils.trim(amount));
        if (value <= 0 ) {
            throw new IllegalArgumentException("Amount should be postive. "+amount);
        }
        return value;
    }
    
    static Campaign fromString(String str) {
        String[] tokens = StringUtils.split(str, ",");
        Validate.isTrue(tokens.length == 3, "Campaign should have 3 values, sparated by commas. There are "+tokens.length+" values in "+str);
        return new Campaign(StringUtils.trim(tokens[0]), parseAmount(tokens[1]), parseAmount(tokens[2]));
    }
    
    public Input getInput() {
        List<String> lines  = readLines();
        Validate.isTrue(lines.size() > 1, "Capacity and at least 1 campaign should be provided. Instead got "+lines);
        int capacity = parseAmount(lines.get(0));
        List<Campaign> campaigns = new ArrayList<Campaign>(lines.size()-1);
        for (String campaign: lines.subList(1, lines.size())) {
            campaigns.add(fromString(campaign));
        }
        return new Input(capacity, campaigns);
    }

    
}
