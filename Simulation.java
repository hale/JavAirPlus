import java.util.ArrayList;
import java.util.Random;
import java.util.Queue;
/**
 * The simulation class simulates passengers checking in
 * to flights at JavAir airlines.
 */
public class Simulation  {
    private ArrayList<Queue<Integer>> desks;
    //private ArrayList<people> deskList;
    private Random rand;
    public Simulation()  {
        desks = new ArrayList();
        desks.add(new Queue<Integer>());
        rand = new Random();
    }
    
    public void startSim(int minutes)  {
        for (int i = 1; i<minutes+1; i++)  {
            if (i%2 == 0)  {
                newPassenger();
            }
            processQueues();
        }
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
    
    private void addPassenger(int time)  {
        int failCount = 0;  // keeps track of the number of full queues
        for (Queue people : desks)  {
            if (people.size() < 4)  {
                people.offer((Integer) time);
            }
            else  {
                failcount++;
            }
        }
        
        if (failCount == desks.size())  { // if all the desks are full
            desks.add(new Queue());
            addPassenger(time);
        }
       
    }
    
    private void processQueues()  {
    }


}