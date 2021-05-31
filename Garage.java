/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/


import java.sql.Time;
import java.util.*;

// line 47 "model.ump"
// line 116 "model.ump"
public class Garage
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum GarageType { Tires, Engine, Transmission, Electronics, Fluids }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Garage Attributes
  private GarageType type;
  private Time startWorkingHours;
  private Time endWorkingHours;

  //Garage Associations
  private List<Technician> technicians;
  private List<Service> services;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Garage(GarageType aType, Time aStartWorkingHours, Time aEndWorkingHours)
  {
    type = aType;
    startWorkingHours = aStartWorkingHours;
    endWorkingHours = aEndWorkingHours;
    technicians = new ArrayList<Technician>();
    services = new ArrayList<Service>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setType(GarageType aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartWorkingHours(Time aStartWorkingHours)
  {
    boolean wasSet = false;
    startWorkingHours = aStartWorkingHours;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndWorkingHours(Time aEndWorkingHours)
  {
    boolean wasSet = false;
    endWorkingHours = aEndWorkingHours;
    wasSet = true;
    return wasSet;
  }

  public GarageType getType()
  {
    return type;
  }

  public Time getStartWorkingHours()
  {
    return startWorkingHours;
  }

  public Time getEndWorkingHours()
  {
    return endWorkingHours;
  }
  /* Code from template association_GetMany */
  public Technician getTechnician(int index)
  {
    Technician aTechnician = technicians.get(index);
    return aTechnician;
  }

  public List<Technician> getTechnicians()
  {
    List<Technician> newTechnicians = Collections.unmodifiableList(technicians);
    return newTechnicians;
  }

  public int numberOfTechnicians()
  {
    int number = technicians.size();
    return number;
  }

  public boolean hasTechnicians()
  {
    boolean has = technicians.size() > 0;
    return has;
  }

  public int indexOfTechnician(Technician aTechnician)
  {
    int index = technicians.indexOf(aTechnician);
    return index;
  }
  /* Code from template association_GetMany */
  public Service getService(int index)
  {
    Service aService = services.get(index);
    return aService;
  }

  public List<Service> getServices()
  {
    List<Service> newServices = Collections.unmodifiableList(services);
    return newServices;
  }

  public int numberOfServices()
  {
    int number = services.size();
    return number;
  }

  public boolean hasServices()
  {
    boolean has = services.size() > 0;
    return has;
  }

  public int indexOfService(Service aService)
  {
    int index = services.indexOf(aService);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTechnicians()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfTechnicians()
  {
    return 2;
  }
  /* Code from template association_AddOptionalNToOne */
  public Technician addTechnician(User aUser, Technician.TechnicianType aType)
  {
    if (numberOfTechnicians() >= maximumNumberOfTechnicians())
    {
      return null;
    }
    else
    {
      return new Technician(aUser, aType, this);
    }
  }

  public boolean addTechnician(Technician aTechnician)
  {
    boolean wasAdded = false;
    if (technicians.contains(aTechnician)) { return false; }
    if (numberOfTechnicians() >= maximumNumberOfTechnicians())
    {
      return wasAdded;
    }

    Garage existingGarage = aTechnician.getGarage();
    boolean isNewGarage = existingGarage != null && !this.equals(existingGarage);
    if (isNewGarage)
    {
      aTechnician.setGarage(this);
    }
    else
    {
      technicians.add(aTechnician);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTechnician(Technician aTechnician)
  {
    boolean wasRemoved = false;
    //Unable to remove aTechnician, as it must always have a garage
    if (!this.equals(aTechnician.getGarage()))
    {
      technicians.remove(aTechnician);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTechnicianAt(Technician aTechnician, int index)
  {  
    boolean wasAdded = false;
    if(addTechnician(aTechnician))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTechnicians()) { index = numberOfTechnicians() - 1; }
      technicians.remove(aTechnician);
      technicians.add(index, aTechnician);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTechnicianAt(Technician aTechnician, int index)
  {
    boolean wasAdded = false;
    if(technicians.contains(aTechnician))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTechnicians()) { index = numberOfTechnicians() - 1; }
      technicians.remove(aTechnician);
      technicians.add(index, aTechnician);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTechnicianAt(aTechnician, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Service addService(String aName, Time aDuration, Owner aOwner, Appointment aAppointment)
  {
    return new Service(aName, aDuration, aOwner, this, aAppointment);
  }

  public boolean addService(Service aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    Garage existingGarage = aService.getGarage();
    boolean isNewGarage = existingGarage != null && !this.equals(existingGarage);
    if (isNewGarage)
    {
      aService.setGarage(this);
    }
    else
    {
      services.add(aService);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeService(Service aService)
  {
    boolean wasRemoved = false;
    //Unable to remove aService, as it must always have a garage
    if (!this.equals(aService.getGarage()))
    {
      services.remove(aService);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addServiceAt(Service aService, int index)
  {  
    boolean wasAdded = false;
    if(addService(aService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServices()) { index = numberOfServices() - 1; }
      services.remove(aService);
      services.add(index, aService);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveServiceAt(Service aService, int index)
  {
    boolean wasAdded = false;
    if(services.contains(aService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServices()) { index = numberOfServices() - 1; }
      services.remove(aService);
      services.add(index, aService);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addServiceAt(aService, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=technicians.size(); i > 0; i--)
    {
      Technician aTechnician = technicians.get(i - 1);
      aTechnician.delete();
    }
    for(int i=services.size(); i > 0; i--)
    {
      Service aService = services.get(i - 1);
      aService.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startWorkingHours" + "=" + (getStartWorkingHours() != null ? !getStartWorkingHours().equals(this)  ? getStartWorkingHours().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endWorkingHours" + "=" + (getEndWorkingHours() != null ? !getEndWorkingHours().equals(this)  ? getEndWorkingHours().toString().replaceAll("  ","    ") : "this" : "null");
  }
}