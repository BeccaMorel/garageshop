/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/


import java.util.*;
import java.sql.Time;

// line 14 "model.ump"
// line 94 "model.ump"
public class Owner extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Owner Associations
  private List<Service> services;
  private GarageInfo garageInfo;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Owner(User aUser)
  {
    super(aUser);
    services = new ArrayList<Service>();
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  /* Code from template association_GetOne */
  public GarageInfo getGarageInfo()
  {
    return garageInfo;
  }

  public boolean hasGarageInfo()
  {
    boolean has = garageInfo != null;
    return has;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Service addService(String aName, Time aDuration, Garage aGarage, Appointment aAppointment)
  {
    return new Service(aName, aDuration, this, aGarage, aAppointment);
  }

  public boolean addService(Service aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    Owner existingOwner = aService.getOwner();
    boolean isNewOwner = existingOwner != null && !this.equals(existingOwner);
    if (isNewOwner)
    {
      aService.setOwner(this);
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
    //Unable to remove aService, as it must always have a owner
    if (!this.equals(aService.getOwner()))
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
  /* Code from template association_SetOptionalOneToOne */
  public boolean setGarageInfo(GarageInfo aNewGarageInfo)
  {
    boolean wasSet = false;
    if (garageInfo != null && !garageInfo.equals(aNewGarageInfo) && equals(garageInfo.getOwner()))
    {
      //Unable to setGarageInfo, as existing garageInfo would become an orphan
      return wasSet;
    }

    garageInfo = aNewGarageInfo;
    Owner anOldOwner = aNewGarageInfo != null ? aNewGarageInfo.getOwner() : null;

    if (!this.equals(anOldOwner))
    {
      if (anOldOwner != null)
      {
        anOldOwner.garageInfo = null;
      }
      if (garageInfo != null)
      {
        garageInfo.setOwner(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=services.size(); i > 0; i--)
    {
      Service aService = services.get(i - 1);
      aService.delete();
    }
    GarageInfo existingGarageInfo = garageInfo;
    garageInfo = null;
    if (existingGarageInfo != null)
    {
      existingGarageInfo.delete();
    }
    super.delete();
  }

}