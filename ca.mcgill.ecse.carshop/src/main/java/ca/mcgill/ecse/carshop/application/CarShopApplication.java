/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ca.mcgill.ecse.carshop.application;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ca.mcgill.ecse.carshop.model.*;
import ca.mcgill.ecse.carshop.model.Technician.TechnicianType;
public class CarShopApplication {
    public String getGreeting() {
        return "Welcome to The Car Shop!";
    }

    public static void main(String[] args) {
        System.out.println(new CarShopApplication().getGreeting());
        
        //configure the car shop
        setup();
        
        //for prompts
        boolean running = true;
        Scanner scan = new Scanner(System.in);
        
        while(running)
        {
        	if(!isLoggedIn())
        	{
	            System.out.println("\nThe Car Shop Main Menu");
	            System.out.println("\t\t1. Login");
	            System.out.println("\t\t2. Signup");
	            System.out.println("\t\t3. Quit");
	            System.out.print("Please Enter Your Option: ");
        	}
        	else
        	{
        		System.out.println("\nWelcome, " + activeUser.getUsername());
	            System.out.println("\t\t1. View/Update Appointment");
	            System.out.println("\t\t2. Book Appointment");
	            System.out.println("\t\t3. Quit");
	            System.out.print("Please Enter Your Option: ");
        	}        	

			int choice = 0;
			try{
				choice = scan.nextInt();
			}
			catch(Exception e)
			{
				System.out.println("Invalid option..");
				continue;
			}

            switch(choice)
            {
				// Login or view/update appointment
                case 1:
                {
                	if(!isLoggedIn())
                	{
						System.out.print("Please Enter Your Username: ");
						String username = scan.next();
						
						System.out.print("Please Enter Your Password: ");
						String password = scan.next();
						
						System.out.println(CarShopApplication.login(username, password));
                	}
                	else
                	{
                		if(activeUser.getAppointments().size() == 0)
                		{
                			System.out.println("You have no appointments");
                		}
                		else
                		{
                			List<Appointment> apts = activeUser.getAppointments();
                			for(Appointment apt : apts)
                			{
	                			System.out.println(apt.getBookableService().getName() + " on " + apt.getServiceBooking(0).getTimeSlot().getStartDate() + " at " + apt.getServiceBooking(0).getTimeSlot().getStartTime());
	
	                			System.out.print("Update? (y/n): ");
	    						if(scan.next().equals("y"))
	    						{
	    							System.out.print("Please Select a New Service (or \"n\" to keep): ");
	    							String service = scan.next();
	    	                		
	    	                		if(BookableService.hasWithName(service))
	    	                		{
	    	                			apt.delete();
	    	                			apt = theCarShop.addAppointment(activeUser, BookableService.getWithName(service));
	    	                		}
	    	                		else if(service.equals("n"))
	    	                		{
	    	                			//do nothing
	    	                		}
	    	                		else
	    	                		{
	    	                			System.out.println("That service does not exist");
	    	                		}
	    	                		
	    	                		System.out.println("The following are available time slots:");
	    	                		
	    	                		List<TimeSlot> slots = theCarShop.getTimeSlots();
	    	                		
	    	                		if(slots.size() == 0)
	    	                		{
	    	                			System.out.println("There are no available time slots.");
	    	                		}
	    	                		else
	    	                		{
	    	                			int counter = 1;
	    	                    		for(TimeSlot slot : slots)
	    	                    		{
	    	                    			System.out.println(counter + ". " + slot.getStartDate() + ", " + slot.getStartTime());
	    	                    			counter++;
	    	                    		}
	    	                    		
	    	                    		System.out.print("Please Select a New Time Slot From Above: ");
	    	                    		int opt = scan.nextInt();
	    	                    		
	    	                    		TimeSlot slot = theCarShop.getTimeSlot(opt-1);
	    	                    		theCarShop.addTimeSlot(usedSlots.get(0));
	    	                    		apt.addServiceBooking((Service) BookableService.getWithName(service), slot);
	    	                    		usedSlots.add(slot);
	    	                    		//TODO: this doesn't remove it from the list
	    	                    		theCarShop.removeTimeSlot(slot);
	    	                		}
	    						}
                			}
                		}
                		
                	}

                    break;
                }
				// Signup or book appointment
                case 2:
                {
                	if(!isLoggedIn())
                	{
                		System.out.print("Please Select a Username: ");
                		String username = scan.next();
                		
                		List<Customer> custs = theCarShop.getCustomers();
                		
                		boolean custExists = false;
                		for(Customer cust : custs)
            		    {
            		    	if(cust.getUsername().equals(username))
            		    	{
            		    		custExists = true;
            		    		System.out.println("That username is taken");
            		    		break;
            		    	}
            		    }
            		    if(!custExists)
            		    {
            		    	System.out.print("Please Select a Password: ");
    						String password = scan.next();
            		    	theCarShop.addCustomer(username, password);
            		    }
                	}
                	else
                	{
                		System.out.print("Please Select a Service: ");
                		String service = scan.next();
                		
                		Appointment apt;
                		if(BookableService.hasWithName(service))
                		{
                			apt = theCarShop.addAppointment(activeUser, BookableService.getWithName(service));
                		}
                		else
                		{
                			System.out.println("That service does not exist");
                			break;
                		}
                		
                		System.out.println("The following are available time slots:");
                		
                		List<TimeSlot> slots = theCarShop.getTimeSlots();
                		
                		if(slots.size() == 0)
                		{
                			System.out.println("There are no available time slots.");
                		}
                		else
                		{
                			int counter = 1;
                    		for(TimeSlot slot : slots)
                    		{
                    			System.out.println(counter + ". " + slot.getStartDate() + ", " + slot.getStartTime());
                    			counter++;
                    		}
                    		
                    		System.out.print("Please Select a Time Slot From Above: ");
                    		int opt = scan.nextInt();
                    		
                    		TimeSlot slot = theCarShop.getTimeSlot(opt-1);
                    		apt.addServiceBooking((Service) BookableService.getWithName(service), slot);
                    		usedSlots.add(slot);
                    		//TODO: this doesn't remove it from the list
                    		theCarShop.removeTimeSlot(slot);
                    		
                		}
                		
                	}
					
                    break;
                }
				// Exit Application
                case 3:
                {
                    running = false;
                    scan.close();
                    activeUser = null;
                    theCarShop.delete();
                    System.out.println("Thank you for using The Car Shop!");
                    break;
                }
				// Invalid
                default:
                {
                    System.out.println("Invalid option \'" + choice + "\'");
                    break;
                }
            }
        }        
    }
    
    /***Make the basic car shop***/
    static CarShop theCarShop = new CarShop();
    static Customer activeUser = null;
    static List<TimeSlot> usedSlots = new ArrayList<TimeSlot>();
    
    private static void setup()
    {
    	theCarShop.addCustomer("user", "test");
    	
    	Owner theOwner = new Owner("owner", "owner", theCarShop);
    	theCarShop.setOwner(theOwner);
    	
    	Technician engineTech = theCarShop.addTechnician("Engine-Technician", "Engine-Technician", TechnicianType.Engine);
    	Garage engineGarage = theCarShop.addGarage(engineTech);
    	
    	theCarShop.addBookableService(new Service("Engine-Swap", theCarShop, 5, engineGarage));
    	theCarShop.addBookableService(new Service("Oil-Change", theCarShop, 1, engineGarage));
    	
    	theCarShop.addTimeSlot(Date.valueOf("2021-06-11"), Time.valueOf("13:00:00"), Date.valueOf("2021-06-11"), Time.valueOf("14:00:00"));
    	theCarShop.addTimeSlot(Date.valueOf("2021-06-14"), Time.valueOf("09:00:00"), Date.valueOf("2021-06-14"), Time.valueOf("14:00:00"));
    }
    
    /***Retrieve the basic car shop***/
    public static CarShop getCarshop() {
    	
		return theCarShop;
    }
    
    /***Methods for login***/
    
    public static boolean isLoggedIn() {
    	
		return (activeUser != null);
    }
    
    public static String login(String username, String pw) {
    	List<Customer> custs = theCarShop.getCustomers();
    	
    	boolean success = false;
    	for(Customer cust : custs)
	    {
	    	if(cust.getUsername().equals(username))
	    	{
	    		if(cust.getPassword().equals(pw))
	    		{
	    			success = true;
	    			activeUser = cust;
	    			break;
	    		}
	    	}
	    }
    	if(!success)
    	{
    		activeUser = null;
    		return "Username/password not found";
    	}
    	else
    	{
    		return "";
    	}
    }
}
