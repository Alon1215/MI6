package bgu.spl.mics.application;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.MissionInfo;
import bgu.spl.mics.application.passiveObjects.Services;
import bgu.spl.mics.application.passiveObjects.Squad;
import bgu.spl.mics.application.publishers.TimeService;
import bgu.spl.mics.application.subscribers.Intelligence;
import bgu.spl.mics.application.subscribers.M;
import bgu.spl.mics.application.subscribers.Moneypenny;
import bgu.spl.mics.application.subscribers.Q;
import com.google.gson.Gson;

import java.io.Reader;
import java.io.IOException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

/** This is the Main class of the application. You should parse the input file, 
 * create the different instances of the objects, and run the system.
 * In the end, you should output serialized objects.
 */
public class MI6Runner {
    public static void main(String[] args) {
       Gson gson=new Gson();

       try(Reader reader = new FileReader("/users/studs/bsc/2020/mosesofe/IdeaProjects/MI6/input201[3].json")){
           JsonParser jsonParser = gson.fromJson(reader,JsonParser.class);
           Inventory inventory = Inventory.getInstance();
           inventory.load(jsonParser.getInventory());
           Squad squad = Squad.getInstance();
           squad.load(jsonParser.getSquad());
           Services service = jsonParser.getServices();

           int mNumber = service.getM();
           int moneyPNumber = service.getMoneypenny();
           MissionInfo[][] intelligences = service.getIntelligence();
           int time = service.getTime();

           //init TimeService
           Thread timeService = new Thread(new TimeService(time)); //TODO: check is right
           timeService.start();
           //init Intelligence:
           for( MissionInfo[] newInfo : intelligences){
               LinkedList<MissionInfo> tmpList= new LinkedList<>();
               for (MissionInfo i : newInfo){
                   tmpList.add(i);
               }
               Thread intel = new Thread(new Intelligence(tmpList));
               System.out.println("before start"); //TODO: delete before submission
               intel.start();
               System.out.println("after start"); //TODO: delete before submission
           }
           System.out.println("created all intelligence"); //TODO: delete before submission
           //init Q:
           Thread qThread = new Thread(new Q());
           qThread.start();
           //init MoneyPenny:
           for (int i = 0; i < moneyPNumber; i++){
               Thread newMoney = new Thread(new Moneypenny(i));
               newMoney.start();
           }
           //init M:
           for (int i = 0; i < mNumber; i++){
               Thread newM = new Thread(new M(i));
               newM.start();
           }
       }
       catch (IOException e){}



    }
}
