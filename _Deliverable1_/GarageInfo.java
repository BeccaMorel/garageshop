/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/


import java.sql.Time;

// line 34 "model.ump"
// line 110 "model.ump"
public class GarageInfo
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GarageInfo Attributes
  private String serviceInfo;
  private String address;
  private String telephone;
  private String email;
  private Time weeklyStartTime;
  private Time weeklyEndTime;
  private Time holidayStartTime;
  private Time holidayEndTime;

  //GarageInfo Associations
  private Owner owner;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GarageInfo(String aServiceInfo, String aAddress, String aTelephone, String aEmail, Time aWeeklyStartTime, Time aWeeklyEndTime, Time aHolidayStartTime, Time aHolidayEndTime, Owner aOwner)
  {
    serviceInfo = aServiceInfo;
    address = aAddress;
    telephone = aTelephone;
    email = aEmail;
    weeklyStartTime = aWeeklyStartTime;
    weeklyEndTime = aWeeklyEndTime;
    holidayStartTime = aHolidayStartTime;
    holidayEndTime = aHolidayEndTime;
    boolean didAddOwner = setOwner(aOwner);
    if (!didAddOwner)
    {
      throw new RuntimeException("Unable to create garageInfo due to owner. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setServiceInfo(String aServiceInfo)
  {
    boolean wasSet = false;
    serviceInfo = aServiceInfo;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setTelephone(String aTelephone)
  {
    boolean wasSet = false;
    telephone = aTelephone;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeeklyStartTime(Time aWeeklyStartTime)
  {
    boolean wasSet = false;
    weeklyStartTime = aWeeklyStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeeklyEndTime(Time aWeeklyEndTime)
  {
    boolean wasSet = false;
    weeklyEndTime = aWeeklyEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setHolidayStartTime(Time aHolidayStartTime)
  {
    boolean wasSet = false;
    holidayStartTime = aHolidayStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setHolidayEndTime(Time aHolidayEndTime)
  {
    boolean wasSet = false;
    holidayEndTime = aHolidayEndTime;
    wasSet = true;
    return wasSet;
  }

  public String getServiceInfo()
  {
    return serviceInfo;
  }

  public String getAddress()
  {
    return address;
  }

  public String getTelephone()
  {
    return telephone;
  }

  public String getEmail()
  {
    return email;
  }

  public Time getWeeklyStartTime()
  {
    return weeklyStartTime;
  }

  public Time getWeeklyEndTime()
  {
    return weeklyEndTime;
  }

  public Time getHolidayStartTime()
  {
    return holidayStartTime;
  }

  public Time getHolidayEndTime()
  {
    return holidayEndTime;
  }
  /* Code from template association_GetOne */
  public Owner getOwner()
  {
    return owner;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setOwner(Owner aNewOwner)
  {
    boolean wasSet = false;
    if (aNewOwner == null)
    {
      //Unable to setOwner to null, as garageInfo must always be associated to a owner
      return wasSet;
    }
    
    GarageInfo existingGarageInfo = aNewOwner.getGarageInfo();
    if (existingGarageInfo != null && !equals(existingGarageInfo))
    {
      //Unable to setOwner, the current owner already has a garageInfo, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Owner anOldOwner = owner;
    owner = aNewOwner;
    owner.setGarageInfo(this);

    if (anOldOwner != null)
    {
      anOldOwner.setGarageInfo(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Owner existingOwner = owner;
    owner = null;
    if (existingOwner != null)
    {
      existingOwner.setGarageInfo(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "serviceInfo" + ":" + getServiceInfo()+ "," +
            "address" + ":" + getAddress()+ "," +
            "telephone" + ":" + getTelephone()+ "," +
            "email" + ":" + getEmail()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "weeklyStartTime" + "=" + (getWeeklyStartTime() != null ? !getWeeklyStartTime().equals(this)  ? getWeeklyStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "weeklyEndTime" + "=" + (getWeeklyEndTime() != null ? !getWeeklyEndTime().equals(this)  ? getWeeklyEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "holidayStartTime" + "=" + (getHolidayStartTime() != null ? !getHolidayStartTime().equals(this)  ? getHolidayStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "holidayEndTime" + "=" + (getHolidayEndTime() != null ? !getHolidayEndTime().equals(this)  ? getHolidayEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "owner = "+(getOwner()!=null?Integer.toHexString(System.identityHashCode(getOwner())):"null");
  }
}