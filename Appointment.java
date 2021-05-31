/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/


import java.sql.Time;
import java.sql.Date;

// line 59 "model.ump"
// line 123 "model.ump"
public class Appointment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Appointment Attributes
  private Time startTime;
  private Time endTime;
  private Date date;

  //Appointment Associations
  private Customer customer;
  private AppointmentCalendar appointmentCalendar;
  private Service service;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Appointment(Time aStartTime, Time aEndTime, Date aDate, Customer aCustomer, AppointmentCalendar aAppointmentCalendar, Service aService)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    date = aDate;
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create appointment due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAppointmentCalendar = setAppointmentCalendar(aAppointmentCalendar);
    if (!didAddAppointmentCalendar)
    {
      throw new RuntimeException("Unable to create appointment due to appointmentCalendar. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aService == null || aService.getAppointment() != null)
    {
      throw new RuntimeException("Unable to create Appointment due to aService. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    service = aService;
  }

  public Appointment(Time aStartTime, Time aEndTime, Date aDate, Customer aCustomer, AppointmentCalendar aAppointmentCalendar, String aNameForService, Time aDurationForService, Owner aOwnerForService, Garage aGarageForService)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    date = aDate;
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create appointment due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAppointmentCalendar = setAppointmentCalendar(aAppointmentCalendar);
    if (!didAddAppointmentCalendar)
    {
      throw new RuntimeException("Unable to create appointment due to appointmentCalendar. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    service = new Service(aNameForService, aDurationForService, aOwnerForService, aGarageForService, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public Date getDate()
  {
    return date;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetOne */
  public AppointmentCalendar getAppointmentCalendar()
  {
    return appointmentCalendar;
  }
  /* Code from template association_GetOne */
  public Service getService()
  {
    return service;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeAppointment(this);
    }
    customer.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAppointmentCalendar(AppointmentCalendar aAppointmentCalendar)
  {
    boolean wasSet = false;
    if (aAppointmentCalendar == null)
    {
      return wasSet;
    }

    AppointmentCalendar existingAppointmentCalendar = appointmentCalendar;
    appointmentCalendar = aAppointmentCalendar;
    if (existingAppointmentCalendar != null && !existingAppointmentCalendar.equals(aAppointmentCalendar))
    {
      existingAppointmentCalendar.removeAppointment(this);
    }
    appointmentCalendar.addAppointment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeAppointment(this);
    }
    AppointmentCalendar placeholderAppointmentCalendar = appointmentCalendar;
    this.appointmentCalendar = null;
    if(placeholderAppointmentCalendar != null)
    {
      placeholderAppointmentCalendar.removeAppointment(this);
    }
    Service existingService = service;
    service = null;
    if (existingService != null)
    {
      existingService.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "appointmentCalendar = "+(getAppointmentCalendar()!=null?Integer.toHexString(System.identityHashCode(getAppointmentCalendar())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "service = "+(getService()!=null?Integer.toHexString(System.identityHashCode(getService())):"null");
  }
}