package com.crap.sms;

import com.crap.sms.controller.Login;
import com.crap.sms.controller.UI;

public class App
{

    public static void main( String[] args )
    {
    	if (Login.login()) {
    		UI ui = new UI();
    		ui.work();
    	}
    }
}