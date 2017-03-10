import com.crap.sms.domain.model.User;
import com.crap.sms.domain.repository.UserRepository;
import junit.framework.TestCase;

/**
 * Created by batkefe on 10.03.2017.
 */
public class UserRepositoryTest extends TestCase {

    public void testSave() {
        User user = new User("test",1234567);
        UserRepository userRepository = UserRepository.getInstance();
        userRepository.save(user);
        User user2 = userRepository.getByUsername("test");
        assertTrue(user.equals(user2));
    }

    public void testDelete() {
        User user = new User("test2",1234567);
        UserRepository userRepository = UserRepository.getInstance();
        userRepository.save(user);
        userRepository.delete(user);
        User user2 = userRepository.getByUsername("test2");
        assertNull(user2);
    }
}
