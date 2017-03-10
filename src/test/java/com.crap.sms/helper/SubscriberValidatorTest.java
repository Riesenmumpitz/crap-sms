package com.crap.sms.helper;

import com.crap.sms.helper.SubscriberValidator;
import junit.framework.TestCase;

/**
 * Created by Martin Geßenich on 10.03.2017.
 */
public class SubscriberValidatorTest extends TestCase {

    public void testisValidForename() {
        assertTrue(SubscriberValidator.isValidForename("Martin-Felix-Caroline-Christoph-ÖÄÜöäü"));
        assertFalse(SubscriberValidator.isValidForename("      "));
    }

    public void testisValidSurname() {
        assertTrue(SubscriberValidator.isValidSurname("Martin-Felix-Caroline-Christoph-ÖÄÜöäü"));
        assertFalse(SubscriberValidator.isValidSurname("      "));
    }

    public void testisValidImsi() {
        assertTrue(SubscriberValidator.isValidImsi("262426516516515"));
        assertFalse(SubscriberValidator.isValidImsi("26242651651651"));
        assertFalse(SubscriberValidator.isValidImsi("2624265i65i6515"));
    }
}
