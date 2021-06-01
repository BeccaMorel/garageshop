/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/


import java.sql.Time;
import java.sql.Date;

// line 73 "model.ump"
// line 136 "model.ump"
public class Service
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Service Attributes
  private String name;
  private Time duration;

  //Service Associations
  private Owner owner;
  private Garage garage;
  private Appointment appointment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Service(String aName, Time aDuration, Owner aOwner, Garage aGarage, Appointment aAppointment)
  {
    name = aName;
    duration = aDuration;
    boolean didAddOwner = setOwner(aOwner);
    if (!didAddOwner)
    {
      throw new RuntimeException("Unable to create service due to owner. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGarage = setGarage(aGarage);
    if (!didAddGarage)
    {
      throw new RuntimeException("Unable to create service due to garage. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aAppointment == null || aAppointment.getService() != null)
    {
      throw new RuntimeException("Unable to create Service due to aAppointment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    appointment = aAppointment;
  }

  public Service(String aName, Time aDuration, Owner aOwner, Garage aGarage, Time aStartTimeForAppointment, Time aEndTimeForAppointment, Date aDateForAppointment, Customer aCustomerForAppointment, AppointmentCalendar aAppointmentCalendarForAppointment)
  {
    name = aName;
    duration = aDuration;
    boolean didAddOwner = setOwner(aOwner);
    if (!didAddOwner)
    {
      throw new RuntimeException("Unable to create service due to owner. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGarage = setGarage(aGarage);
    if (!didAddGarage)
    {
      throw new RuntimeException("Unable to create service due to garage. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    appointment = new Appointment(aStartTimeForAppointment, aEndTimeForAppointment, aDateForAppointment, aCustomerForAppointment, aAppointmentCalendarForAppointment, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setDuration(Time aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Time getDuration()
  {
    return duration;
  }
  /* Code from template association_GetOne */
  public Owner getOwner()
  {
    return owner;
  }
  /* Code from template association_GetOne */
  public Garage getGarage()
  {
    return garage;
  }
  /* Code from template association_GetOne */
  public Appointment getAppointment()
  {
    return appointment;
  }
  /* Code from template association_SetOneToMany */
  public boolean setOwner(Owner aOwner)
  {
    boolean wasSet = false;
    if (aOwner == null)
    {
      return wasSet;
    }

    Owner existingOwner = owner;
    owner = aOwner;
    if (existingOwner != null && !existingOwner.equals(aOwner))
    {
      existingOwner.removeService(this);
    }
    owner.addService(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGarage(Garage aGarage)
  {
    boolean wasSet = false;
    if (aGarage == null)
    {
      return wasSet;
    }

    Garage existingGarage = garage;
    garage = aGarage;
    if (existingGarage != null && !existingGarage.equals(aGarage))
    {
      existingGarage.removeService(this);
    }
    garage.addService(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Owner placeholderOwner = owner;
    this.owner = null;
    if(placeholderOwner != null)
    {
      placeholderOwner.removeService(this);
    }
    Garage placeholderGarage = garage;
    this.garage = null;
    if(placeholderGarage != null)
    {
      placeholderGarage.removeService(this);
    }
    Appointment existingAppointment = appointment;
    appointment = null;
    if (existingAppointment != null)
    {
      existingAppointment.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "duration" + "=" + (getDuration() != null ? !getDuration().equals(this)  ? getDuration().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "owner = "+(getOwner()!=null?Integer.toHexString(System.identityHashCode(getOwner())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "garage = "+(getGarage()!=null?Integer.toHexString(System.identityHashCode(getGarage())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "appointment = "+(getAppointment()!=null?Integer.toHexString(System.identityHashCode(getAppointment())):"null");
  }
}