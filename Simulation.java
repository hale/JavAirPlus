import java.util.ArrayList;
import java.util.Random;
import java.util.ArrayDeque;
/**
 * The simulation class simulates passengers checking in
 * to flights at JavAir airlines.
 */
public class Simulation  {
    private ArrayList<ArrayDeque<Integer>> desks;
    private Random rand;
    public static final int MAX_QUEUE_SIZE = 4;
    public Simulation(int minutes)  {
        desks = new ArrayList();
        rand = new Random();
        startSim(minutes);
    }
    
    public void startSim(int minutes)  {
        for (int i = 1; i<minutes+1; i++)  {
            if (i%2 == 0)  {
                newPassenger(); // Adds a new passenger every other minute.
            }
            processQueues();
        }
        System.out.println("The simulation has completed.");
        System.out.println("    Passengers checked in: " + minutes);
        System.out.println("    Minimum number of check in desks required: " + desks.size());
    }
    
    private void newPassenger()  {
        int i = rand.nextInt(100);
        if (i < 20)  {
            addPassenger(10);
        }
        if (i > 19 && i< 50)  {
            addPassenger(8);
        }
        if (i > 49)  {
            addPassenger(6);
        }
        
    }
    
    private boolean addPassenger(int time)  {
        int failCount = 0;  // keeps track of the number of full queues
        for (ArrayDeque<Integer> desk : desks)  {
            if (desk.size() < MAX_QUEUE_SIZE)  {
                return desk.offer((Integer) time);
            }
            else  {
                failCount++;
            }
        }
        
        if (failCount == desks.size())  { // if all the desks are full
            desks.add(new ArrayDeque<Integer>());
            addPassenger(time);
        }
        return false;
    }
    
    private void processQueues()  {
        for (ArrayDeque<Integer> desk : desks)  {
            if (desk.isEmpty())  {
                break;
            }
            int passenger = desk.peek().intValue();
            passenger--;
            desk.pop();
            desk.push(passenger);
            
            if (desk.peek().intValue() == 0)  {
                desk.pop();
            }
        }
        
    }


}