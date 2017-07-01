package model;

/**
 * Created by harri on 01/07/2017.
 */

public class Weather {

    public Place place;
    public String iconData;
    public CurrentConditions currentConditions = new CurrentConditions();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
    public Rain rain = new Rain();
    public Snow snow = new Snow();
    public Clouds clouds = new Clouds();

}
