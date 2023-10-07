package MinorProject;

import	org.firmata4j.Pin;
import	org.firmata4j.ssd1306.SSD1306;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MinorTask extends TimerTask{

    private	final	Pin	moisturesensPin;
    private	final	Pin	pumpPin;
    private final Timer myTimer;
    private	final	SSD1306	myOled;



    public	MinorTask(Pin moisturesensPin,	Pin	pumpPin, Timer	timer,SSD1306	display	)	{

        this.myOled =	display;
        this.moisturesensPin = moisturesensPin;
        this.pumpPin =	pumpPin;
        this.myTimer	=	timer;

    }
    @Override
    public	void	run() {

        double dry = 760;
        double normal = 650;
        double wet = 560;

        try {
            while (true) {

                //myOled.getCanvas().drawString(0, 20, "Moisture : " + moisturesensPin.getValue());
                //myOled.display();

                if (moisturesensPin.getValue() < wet) {
                    pumpPin.setValue(0);
                    myOled.getCanvas().drawString(0, 0, " Soil is Wet");
                    myOled.getCanvas().drawString(0, 20, " Value of Moisture " + moisturesensPin.getValue());
                    myOled.display();
                    myOled.getCanvas().clear();
                } else if (moisturesensPin.getValue() < normal && moisturesensPin.getValue() > wet) {
                    pumpPin.setValue(1);
                    myOled.getCanvas().drawString(0, 0, " Soil needs a little more water");
                    myOled.getCanvas().drawString(0, 20, " Value of Moisture " + moisturesensPin.getValue());
                    myOled.display();
                    myOled.getCanvas().clear();
                } else if (moisturesensPin.getValue() < dry && moisturesensPin.getValue() > normal) {
                    pumpPin.setValue(1);
                    myOled.getCanvas().drawString(0, 0, " Soil needs Water");
                    myOled.getCanvas().drawString(0, 20, " Value of Moisture" + moisturesensPin.getValue());
                    myOled.display();
                    myOled.getCanvas().clear();


                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

}
