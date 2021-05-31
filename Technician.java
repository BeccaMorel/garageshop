/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.2.5248.dba0a5744 modeling language!*/



// line 20 "model.ump"
// line 100 "model.ump"
public class Technician extends UserRole
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum TechnicianType { Tires, Engine, Transmission, Electronics, Fluids }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Technician Attributes
  private TechnicianType type;

  //Technician Associations
  private Garage garage;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Technician(User aUser, TechnicianType aType, Garage aGarage)
  {
    super(aUser);
    type = aType;
    boolean didAddGarage = setGarage(aGarage);
    if (!didAddGarage)
    {
      throw new RuntimeException("Unable to create technician due to garage. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setType(TechnicianType aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public TechnicianType getType()
  {
    return type;
  }
  /* Code from template association_GetOne */
  public Garage getGarage()
  {
    return garage;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setGarage(Garage aGarage)
  {
    boolean wasSet = false;
    //Must provide garage to technician
    if (aGarage == null)
    {
      return wasSet;
    }

    //garage already at maximum (2)
    if (aGarage.numberOfTechnicians() >= Garage.maximumNumberOfTechnicians())
    {
      return wasSet;
    }
    
    Garage existingGarage = garage;
    garage = aGarage;
    if (existingGarage != null && !existingGarage.equals(aGarage))
    {
      boolean didRemove = existingGarage.removeTechnician(this);
      if (!didRemove)
      {
        garage = existingGarage;
        return wasSet;
      }
    }
    garage.addTechnician(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Garage placeholderGarage = garage;
    this.garage = null;
    if(placeholderGarage != null)
    {
      placeholderGarage.removeTechnician(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "garage = "+(getGarage()!=null?Integer.toHexString(System.identityHashCode(getGarage())):"null");
  }
}