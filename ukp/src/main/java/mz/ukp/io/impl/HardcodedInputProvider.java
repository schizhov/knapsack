package mz.ukp.io.impl;

import java.util.Arrays;
import mz.ukp.io.Campaign;
import mz.ukp.io.Input;
import mz.ukp.io.InputProvider;

/**
 * Some inputs.
 * 
 * @author stanislav.chizhov
 */
public class HardcodedInputProvider implements InputProvider {

    private final static Input SAMPLE = new Input(32356000, Arrays.asList(
            new Campaign("Acme", 2000000, 200),
            new Campaign("Lorem", 3500000, 400),
            new Campaign("Ipsum", 2300000, 210),
            new Campaign("Dolor", 8000000, 730),
            new Campaign("SIT", 10000000, 1000),
            new Campaign("Amet", 1500000, 160),
            new Campaign("Mauris", 1000000, 100)));
    
    private final static Input S1 = new Input(11, Arrays.asList(
            new Campaign("Acme", 3, 3),
            new Campaign("Lorem", 5, 5),
            new Campaign("Ipsum", 7, 7)));

    private final static Input BIG = new Input(100000010, Arrays.asList(
            new Campaign("Acme", 5, 5),
            new Campaign("Acme", 7, 7),
            new Campaign("Acme", 3, 3)
            ));
    
    private final static Input BIG2 = new Input(100005231, Arrays.asList(
            new Campaign("-1023-", 1023, 1023),
            new Campaign("-1007-", 1007, 1007),
            new Campaign("-4013-", 4013, 4013),
            new Campaign("-41-", 41, 41),
            new Campaign("-53-", 53, 53),
            new Campaign("-111-", 111, 111),
            new Campaign("-3-", 3, 3)
            ));

    private final static Input BIG3 = new Input(100005231, Arrays.asList(
            new Campaign("-1023-", 1023, 1023),
            new Campaign("-1007-", 1007, 1007),
            new Campaign("-4013-", 4013, 4013),
            new Campaign("-41-", 41, 41),
            new Campaign("-53-", 53, 53),
            new Campaign("-11111111-", 11111111, 11111123),
            new Campaign("-11111111-", 11111111, 11111111),
            new Campaign("-11111111-", 11111112, 11111112),
            new Campaign("-11111111-", 11111113, 11111113),
            new Campaign("-300001", 300001, 300001)
            ));

    private final static Input S2 = new Input(12, Arrays.asList(
            new Campaign("Acme", 5, 5),
            new Campaign("Lorem", 3, 3)));

    
    public Input getInput() {
        return S2;
    }
}
