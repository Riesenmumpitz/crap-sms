import com.crap.sms.domain.model.RAN;
import com.crap.sms.domain.model.Subscriber;
import com.crap.sms.domain.model.Subscription;
import com.crap.sms.domain.model.Terminal;
import com.crap.sms.domain.repository.SubscriberRepository;
import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by batkefe on 10.03.2017.
 */
public class SubscriberRepositoryTest extends TestCase {

    public void testSave() {
        Set<RAN> ran = new HashSet<RAN>();
        ran.add(RAN.G2);
        ran.add(RAN.G3);
        Terminal terminal = new Terminal(ran);
        Subscription subscription = new Subscription(100,100,2,20);
        Subscriber subscriber = new Subscriber("123456789012345",terminal,subscription,"Test","Tester");
        SubscriberRepository subscriberRepository = SubscriberRepository.getInstance();
        subscriberRepository.save(subscriber);
        Subscriber subscriber2 = subscriberRepository.getByImsi("123456789012345");
        assertTrue(subscriber.equals(subscriber2));
    }
}
